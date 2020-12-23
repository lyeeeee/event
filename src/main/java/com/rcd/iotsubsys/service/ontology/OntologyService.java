package com.rcd.iotsubsys.service.ontology;

import com.rcd.iotsubsys.common.directory.DirectoryNodeOwner;
import com.rcd.iotsubsys.config.Constant;
import com.rcd.iotsubsys.domain.directory.DirectoryNode;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeFile;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeKnowledge;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeRepository;
import com.rcd.iotsubsys.service.directory.KnowledgeDirectoryService;
import com.rcd.iotsubsys.util.OwlClass;
import com.rcd.iotsubsys.util.OwlResourceData;
import com.rcd.iotsubsys.util.OwlResourceUtil;
import com.rcd.iotsubsys.util.equivalent_class;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.eclipse.rdf4j.query.algebra.Str;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;


/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-21 23:12
 */
@Service
public class OntologyService{

    private static final Dataset database = TDBFactory.createDataset(Constant.TDB_BASE_DIR);

    private static final Logger LOGGER = LoggerFactory.getLogger(OntologyService.class);

    /**
     * 文件名 + OwlResourceData
     * */
    private static Map<String, OwlResourceData> owlResourceDataMap = new HashMap<>();

    static {
        File rootDir = new File(Constant.ONTOLOGY_FILE_DIR);
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }
        File[] files = rootDir.listFiles();
        Arrays.stream(files).forEach( file -> {
            OwlResourceData data = OwlResourceUtil.parseResourceFile(Constant.ONTOLOGY_FILE_DIR + file.getName());
            owlResourceDataMap.put(file.getName(), data);
        });
    }

    @Autowired
    private KnowledgeDirectoryService knowledgeDirectoryService;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    /**
     * 增加新模型
     * */
    public void addModel(String modelName, MultipartFile file) throws IOException {
        database.begin(ReadWrite.WRITE);
        InputStream inputStream = null;
        InputStream in = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = file.getInputStream();
            File newFile = new File(Constant.ONTOLOGY_FILE_DIR + file.getOriginalFilename());
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            IOUtils.copy(inputStream, outputStream);
            Model model = ModelFactory.createDefaultModel();
            in = FileUtils.openInputStream(new File(Constant.ONTOLOGY_FILE_DIR + file.getOriginalFilename()));
            model.read(in, null);
            database.addNamedModel(modelName, model);
            database.commit();
        } catch (IOException e) {
            e.printStackTrace();
            database.abort();
            throw e;
        } finally {
            inputStream.close();
            in.close();
            outputStream.close();
            database.end();
        }
    }

    /**
     * 构建owl本体，解析传感器资源
     * */
    public void resolveKnowledge(String modelName, List<Long> metaId, long fileId) {
        database.begin(ReadWrite.READ);
        Model model = database.getNamedModel(modelName);
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, model);
        // 获取传感器
        OntClass featureOfInterest = ontModel.getOntClass(Constant.SSN_FEATURE_OF_INTEREST_URI);
        ExtendedIterator<OntClass> ontClassExtendedIterator = featureOfInterest.listSubClasses();
        List<OntClass> subClass = ontClassExtendedIterator.toList();
        resolveKnowledgeAndDirectory(subClass, metaId.get(metaId.size()-1), fileId, modelName);
        database.commit();
        database.end();
    }

    /**
     * 解析FeatureOfInterest，并增加对应的目录结构。
     * */
    private void resolveKnowledgeAndDirectory(List<OntClass> ontClasss, Long metaId, long fileId, String modelName) {
        if (ObjectUtils.isEmpty(ontClasss)) {
            return;
        } else {
            for (int i = 0;i < ontClasss.size(); ++i) {
                OntClass tmp = ontClasss.get(i);
                DirectoryNode node = new DirectoryNode();
                node.setParentId(metaId);
                node.setnName(Constant.KNOWlEDGE);
                if (!tmp.hasSubClass()) node.setOwner(DirectoryNodeOwner.KNOWLEDGE.getOwner());
                else node.setOwner(DirectoryNodeOwner.KNOWLEDGE_CLASS.getOwner());
                node.setValue(tmp.getURI());
                JsonResult<Object> result = knowledgeDirectoryService.addOrUpdateDirectoryNode(node);
                node = (DirectoryNode) result.getData();
                // 有子类继续深搜
                if (tmp.hasSubClass()) {
                    resolveKnowledgeAndDirectory(tmp.listSubClasses().toList(), node.getId(), fileId, modelName);
                } else {// 没有子类，创建知识，并且如果知识有实例就创建实例

                    // 创建知识
                    KnowledgeKnowledge knowledge = new KnowledgeKnowledge();
                    knowledge.setType(0);
                    knowledge.setKnowledgeUri(tmp.getURI());
                    knowledge.setKnowledgeName(tmp.getLocalName());
                    knowledge.setKnowledgeDir(metaId);
                    knowledge.setKnowldegeFileId(fileId);
                    knowledge.setDirNodeId(node.getId());
                    knowledge.setModelName(modelName);
                    KnowledgeKnowledge ret = knowledgeRepository.save(knowledge);

                    List<? extends OntResource> ontResources = tmp.listInstances().toList();
                    // 最底层类有实例,创建实例知识和实例目录结点
                    if (!CollectionUtils.isEmpty(ontResources)) {
                        ontResources.forEach(o -> {
                            // 目录树中的实例结点
                            DirectoryNode instanceDirNode = new DirectoryNode();
                            instanceDirNode.setParentId(metaId);
                            instanceDirNode.setnName(Constant.KNOWLEDGE_INSTACNE);
                            instanceDirNode.setOwner(DirectoryNodeOwner.KNOWLEDGE_INSTANCE.getOwner());
                            instanceDirNode.setValue(o.getURI());
                            instanceDirNode = (DirectoryNode) (knowledgeDirectoryService.addOrUpdateDirectoryNode(instanceDirNode)).getData();

                            // 创建实例知识
                            KnowledgeKnowledge knowledgeInstance = new KnowledgeKnowledge();
                            knowledgeInstance.setKnowldegeFileId(fileId);
                            knowledgeInstance.setKnowledgeClassId(ret.getId());
                            knowledgeInstance.setKnowledgeDir(metaId);
                            knowledgeInstance.setKnowledgeName(o.getLocalName());
                            knowledgeInstance.setKnowledgeUri(o.getURI());
                            knowledgeInstance.setType(1);
                            knowledgeInstance.setDirNodeId(instanceDirNode.getId());
                            knowledgeInstance.setModelName(modelName);
                            knowledgeRepository.save(knowledgeInstance);
                        });
                    }
                }
            }
        }
    }

    public List<String> getKnowledgeProperties(String fileName, String modelName, String knowledgeUri) {
        OwlResourceData owlResourceData = owlResourceDataMap.get(fileName);
        if (ObjectUtils.isEmpty(owlResourceData)) {
            return new ArrayList<>();
        }
        List<String> ret = new ArrayList<>();
        Map<String, OwlClass> classMap = owlResourceData.classMap;
        OwlClass owlClass = classMap.get(knowledgeUri);
        if (owlClass == null) {
            owlClass = owlResourceData.objMap.get(knowledgeUri).type;
        }
        Map<String, equivalent_class> equivalentClass = owlClass.equivalentClass;
        equivalentClass.forEach((k, v) -> {
            String propertyUri = v.ValuesFrom;
            ret.add(propertyUri);
        });
        return ret;
    }

    public void deleteModel(KnowledgeFile file) {
        String tdbModelName = file.getTdbModelName();
        database.begin(ReadWrite.WRITE);
        database.removeNamedModel(tdbModelName);
        database.commit();
        LOGGER.info("delete tdb model: {}", tdbModelName);
        FileUtils.deleteQuietly(new File(Constant.ONTOLOGY_FILE_DIR + file.getFileName()));
        LOGGER.info("delete model file: {}", file.getFileName());
        if (owlResourceDataMap.get(file.getFileName()) != null ) {
            owlResourceDataMap.remove(file.getFileName());
            LOGGER.info("remove owlResource: {}", file.getFileName());
        }
    }

    public ResultSet getKnowledge(String modelName, String sql) {
        database.begin(ReadWrite.READ);
        Model model = database.getNamedModel(modelName);
        Query query = QueryFactory.create(sql);
        QueryExecution queryExec = QueryExecutionFactory.create(query, model);
        ResultSet results = queryExec.execSelect();
        database.commit();
        database.end();
        return results;
    }
}
