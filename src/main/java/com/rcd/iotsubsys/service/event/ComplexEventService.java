package com.rcd.iotsubsys.service.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexEvent;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexSubEvnet;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexTarget;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.repository.event.ComplexEventRepository;
import com.rcd.iotsubsys.repository.event.KnowledgeComplexSubEventRepository;
import com.rcd.iotsubsys.repository.event.KnowledgeComplexTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-04 18:20
 */
@Service
public class ComplexEventService {

    @Autowired
    private ComplexEventRepository complexEventRepository;

    @Autowired
    private KnowledgeComplexSubEventRepository knowledgeComplexSubEventRepository;

    @Autowired
    private KnowledgeComplexTargetRepository knowledgeComplexTargetRepository;

    public JsonResult<Object> addComplexEvent(KnowledgeComplexEvent knowledgeComplexEvent) {
        if (knowledgeComplexEvent.getId() != null) {
            KnowledgeComplexEvent one = complexEventRepository.getOne(knowledgeComplexEvent.getId());
            one.setName(knowledgeComplexEvent.getName());
            one.setSynopsis(knowledgeComplexEvent.getSynopsis());
            return new JsonResult<>(complexEventRepository.save(one));
        }
        KnowledgeComplexEvent save = complexEventRepository.save(knowledgeComplexEvent);
        return new JsonResult<>(save);
    }

    public JsonResult<Object> getAllComplexEvent(String name) {
        if (StringUtils.isEmpty(name)) {
            return new JsonResult<>(complexEventRepository.findAll());
        } else {
            return new JsonResult<>(complexEventRepository.findAllByName(name));
        }
    }

    public JsonResult<Object> addComplexSubEvnet(KnowledgeComplexSubEvnet subEvnet) {
        if (ObjectUtils.isEmpty(subEvnet)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(knowledgeComplexSubEventRepository.save(subEvnet));
        }
    }

    public JsonResult<Object> getAllSubEvent(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(knowledgeComplexSubEventRepository.getAllByComplexEventId(complexEventId));
        }
    }

    public JsonResult<Object> deleteComplexEvent(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            List<KnowledgeComplexSubEvnet> allByComplexEventId = knowledgeComplexSubEventRepository.getAllByComplexEventId(complexEventId);
            complexEventRepository.deleteById(complexEventId);
            knowledgeComplexSubEventRepository.deleteAll(allByComplexEventId);
            return new JsonResult<>();
        }
    }

    public JsonResult<Object> deleteComplexSubEvent(Long complexSubEventId) {
        if (ObjectUtils.isEmpty(complexSubEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            knowledgeComplexSubEventRepository.deleteById(complexSubEventId);
            return new JsonResult<>();
        }
    }

    public JsonResult<Object> addMetaEventRelation(Long complexEventId, String relation) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            KnowledgeComplexEvent one = complexEventRepository.getOne(complexEventId);
            one.setRelation(relation);
            return new JsonResult<>(complexEventRepository.save(one));
        }
    }

    public JsonResult<Object> addComplexTarget(KnowledgeComplexTarget knowledgeComplexTarget) {
        if (ObjectUtils.isEmpty(knowledgeComplexTarget)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(knowledgeComplexTargetRepository.save(knowledgeComplexTarget));
        }
    }

    public JsonResult<Object> getAllTarget(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(knowledgeComplexTargetRepository.getAllByComplexEventId(complexEventId));
        }
    }

    public JsonResult<Object> addTargetRelation(Long complexEventId, String relation) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            KnowledgeComplexEvent one = complexEventRepository.getOne(complexEventId);
            one.setTargetRelation(relation);
            return new JsonResult<>(complexEventRepository.save(one));
        }
    }
}
