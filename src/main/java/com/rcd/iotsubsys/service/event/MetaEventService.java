package com.rcd.iotsubsys.service.event;

import com.rcd.iotsubsys.domain.event.MetaEventAttrRelation;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeKnowledge;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.repository.event.MetaEventAttrRelationRepository;
import com.rcd.iotsubsys.repository.event.MetaEventRepository;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeRepository;
import com.rcd.iotsubsys.service.knowledge.KnowledgeService;
import com.rcd.iotsubsys.service.ontology.OntologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-25 10:48
 */
@Service
public class MetaEventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaEventService.class);

    @Autowired
    private MetaEventRepository metaEventRepository;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private MetaEventAttrRelationRepository metaEventAttrRelationRepository;

    public JsonResult<Object> addMetaEvent(KnowledgeMetaEvent knowledgeMetaEvent) {
        KnowledgeMetaEvent tmp = metaEventRepository.findByName(knowledgeMetaEvent.getName());
        if (tmp != null) {
            return new JsonResult<>(ResponseCode.EVENT_METAEVENT_EXIST);
        }
        KnowledgeKnowledge knowledge = knowledgeRepository.getKnowledgeKnowledgeByDirNodeId(knowledgeMetaEvent.getKnowledgeId());
        knowledgeMetaEvent.setKnowledgeId(knowledge.getId());
        knowledgeMetaEvent.setKnowledge(knowledge.getKnowledgeName());
        KnowledgeMetaEvent save = metaEventRepository.save(knowledgeMetaEvent);
        return new JsonResult<>(save);
    }

    public JsonResult<Object>  getAllMetaEvent(String name) {
        List<KnowledgeMetaEvent> list = metaEventRepository.findAll();
        if (StringUtils.isEmpty(name)) {
            return new JsonResult<>(list);
        } else {

            return new JsonResult<>(list.stream().filter(event -> event.getName().contains(name)).collect(Collectors.toList()));
        }
    }

    public JsonResult<Object> getRelationMap(Long relationId) {
        MetaEventAttrRelation metaEventAttrRelation = metaEventAttrRelationRepository.getOne(relationId);
        return new JsonResult<>(metaEventAttrRelation);
    }

    public JsonResult<Object> addMetaEventRelation(MetaEventAttrRelation metaEventAttrRelation) {
        metaEventAttrRelation.setKnowledgeAttributeType(metaEventAttrRelation.getTopicAttributeType());
        MetaEventAttrRelation save = metaEventAttrRelationRepository.save(metaEventAttrRelation);
        return new JsonResult<>(save);
    }

    public JsonResult<Object> getAllRelation(Long metaEventId) {
        List<MetaEventAttrRelation> list = metaEventAttrRelationRepository.findAllByMetaEventId(metaEventId);
        if (CollectionUtils.isEmpty(list)) {
            return new JsonResult<>(new ArrayList<>());
        }
        return new JsonResult<>(list);
    }

    public JsonResult<Object> getAllRelationByIds(List<Long> metaEventIds) {
        List<MetaEventAttrRelation> list = metaEventAttrRelationRepository.findAllByMetaEventIdIn(metaEventIds);
        if (CollectionUtils.isEmpty(list)) {
            return new JsonResult<>(new ArrayList<>());
        }
        return new JsonResult<>(list);
    }

    public JsonResult<Object> deleteRelation(Long relationId) {
        metaEventAttrRelationRepository.deleteById(relationId);
        return new JsonResult<>();
    }

    @Transactional
    public JsonResult<Object> deleteMetaEvent(Long metaEventId) {
        metaEventRepository.deleteById(metaEventId);
        metaEventAttrRelationRepository.deleteAllByMetaEventId(metaEventId);
        return new JsonResult<>();
    }

    public JsonResult<Object> getMetaEventByIds(List<Long> ids) {
        return new JsonResult<>(metaEventRepository.findAllByIdIn(ids));
    }
}
