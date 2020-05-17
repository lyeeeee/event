package com.rcd.iotsubsys.service.knowledge;

import com.rcd.iotsubsys.domain.directory.DirectoryNode;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeDetail;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeFile;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeKnowledge;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.factory.KnowledgeFileFactory;
import com.rcd.iotsubsys.repository.directory.DirectoryRepository;
import com.rcd.iotsubsys.repository.event.MetaEventRepository;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeFileRepository;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeRepository;
import com.rcd.iotsubsys.service.ontology.OntologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-21 16:05
 */
@Service
public class KnowledgeService {

    @Autowired
    private KnowledgeFileRepository knowledgeFileRepository;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private OntologyService ontologyService;

    @Autowired
    private MetaEventRepository metaEventRepository;

    @Autowired
    private DirectoryRepository directoryRepository;

    public JsonResult<Object> checkUploadKnowledge(Map<String, Object> map) throws IOException {
        String name = (String) map.get("name");
        String graphName = (String)map.get("graphName");
        List<KnowledgeFile> all = knowledgeFileRepository.findAll();
        Set<String> names = all.stream().map(k -> k.getFileName()).collect(Collectors.toSet());
        Set<String> graphNames = all.stream().map(k -> k.getGraphId()).collect(Collectors.toSet());
        if (names.contains(name) || graphNames.contains(graphName)) {
            return new JsonResult<>(ResponseCode.KNOWLEDGE_MODEL_DUPLIC);
        }
        //保存文件信息
        KnowledgeFile file = new KnowledgeFile();
        KnowledgeFileFactory.fillPropertiesKnowledgeFile(file, map);
        file = knowledgeFileRepository.save(file);

        String modelName = file.getModelName();
        ontologyService.addModel(modelName, (MultipartFile)map.get("file"));
        ontologyService.resolveKnowledge(modelName, (List)map.get("metaId"), file.getId());
        return new JsonResult<>();
    }

    public JsonResult<Object> getKnowledgeProperties(Long metaEventId) {
        long id = metaEventId;
        System.out.println(id);
        KnowledgeMetaEvent metaEvent = metaEventRepository.getOne(id);
        System.out.println(metaEvent.getKnowledgeId());
        KnowledgeKnowledge knowledge = knowledgeRepository.getOne(metaEvent.getKnowledgeId());
        long fileId = knowledge.getKnowldegeFileId();
        KnowledgeFile file = knowledgeFileRepository.getOne(fileId);
        List<String> knowledgeProperties = ontologyService.getKnowledgeProperties(file.getFileName(),file.getModelName(), knowledge.getKnowledgeUri());
        return new JsonResult<>(knowledgeProperties);
    }

    public JsonResult<Object> getKnowledge(String knowledgeName, Long field, Long department, Long metaDir) {
        List<KnowledgeDetail> ret = new ArrayList<>();
        List<KnowledgeKnowledge> knowledges = knowledgeRepository.findAll();
        System.out.println("knowledges  size:" + knowledges.size()  + "===================");
//        if (!ObjectUtils.isEmpty(metaDir)) {
//            knowledges = knowledges.stream()
//                .filter(knowledgeKnowledge -> knowledgeKnowledge.getKnowledgeDir().equals(metaDir))
//                .collect(Collectors.toList());
//            System.out.println("knowledges  size:" + knowledges.size()  + "===================");
//        } else if (!ObjectUtils.isEmpty(department))  {
//            knowledges = knowledges.stream()
//                .filter(knowledgeKnowledge -> knowledgeKnowledge.getKnowledgeDir().equals(department))
//                .collect(Collectors.toList());
//            System.out.println("knowledges  size:" + knowledges.size()  + "===================");
//        } else if (!ObjectUtils.isEmpty(field))  {
//            knowledges = knowledges.stream()
//                .filter(knowledgeKnowledge -> knowledgeKnowledge.getKnowledgeDir().equals(field))
//                .collect(Collectors.toList());
//            System.out.println("knowledges  size:" + knowledges.size()  + "===================");
//        }
        if (!ObjectUtils.isEmpty(knowledgeName)) {
            knowledges = knowledges.stream()
                .filter(knowledgeKnowledge -> knowledgeKnowledge.getKnowledgeName().contains(knowledgeName))
                .collect(Collectors.toList());
            System.out.println("knowledges  size:" + knowledges.size()  + "===================");
        }
        for (int i = 0;i < knowledges.size(); ++i) {
            KnowledgeKnowledge elem = knowledges.get(i);
            Long cur = elem.getKnowledgeDir();
            List<DirectoryNode> pre = new ArrayList<>();
            while (cur != -1) {
                DirectoryNode node = directoryRepository.getOne(cur);
                pre.add(node);
                cur = node.getParentId();
            }
            KnowledgeDetail detail = new KnowledgeDetail();
            detail.setKnowledgeName(elem.getKnowledgeName());
            detail.setKnowledgeId(elem.getId());
            detail.setField(pre.get(pre.size()-2).getValue());
            detail.setFieldId(pre.get(pre.size()-2).getId());
            detail.setDepartment(pre.get(pre.size()-3).getValue());
            detail.setDepartmentId(pre.get(pre.size()-3).getId());
            detail.setMetaDir(pre.get(0).getValue());
            detail.setMetaDirId(pre.get(0).getId());
            detail.setKnowledgeSynopsis(null);
            ret.add(detail);
        }
        if (!ObjectUtils.isEmpty(department)) {
            ret = ret.stream().filter(detail-> detail.getDepartmentId().equals(department)).collect(Collectors.toList());
        }
        if (!ObjectUtils.isEmpty(field)) {
            ret = ret.stream().filter(detail-> detail.getFieldId().equals(field)).collect(Collectors.toList());
        }
        if (!ObjectUtils.isEmpty(metaDir)) {
            ret = ret.stream().filter(detail-> detail.getMetaDirId().equals(metaDir)).collect(Collectors.toList());
        }
        return new JsonResult<>(ret);
    }
}
