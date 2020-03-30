package com.rcd.iotsubsys.service.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeFile;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeKnowledge;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.factory.KnowledgeFileFactory;
import com.rcd.iotsubsys.repository.event.MetaEventRepository;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeFileRepository;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeRepository;
import com.rcd.iotsubsys.service.ontology.OntologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
}
