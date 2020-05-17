package com.rcd.iotsubsys.service.event;

import com.rcd.iotsubsys.domain.knowledge.*;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.repository.event.*;
import com.rcd.iotsubsys.service.deduce.DeduceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private MetaEventRepository metaEventRepository;

    @Autowired
    private KnowledgeComplexSubEventRepository knowledgeComplexSubEventRepository;

    @Autowired
    private KnowledgeComplexTargetRepository knowledgeComplexTargetRepository;

    @Autowired
    private KnowledgeComplexSubeventRelationRepository knowledgeComplexSubeventRelationRepository;

    @Autowired
    private KnowledgeComplexTargetRelationRepository knowledgeComplexTargetRelationRepository;

    @Autowired
    private DeduceContext deduceContext;

    public JsonResult<Object> getComplexEventById(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(complexEventRepository.getOne(complexEventId));
        }
    }

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
        List<KnowledgeComplexEvent> list = complexEventRepository.findAll();
        if (StringUtils.isEmpty(name)) {
            return new JsonResult<>(list);
        } else {
            return new JsonResult<>(list.stream().filter(event -> event.getName().contains(name)).collect(Collectors.toList()));
        }
    }

    public JsonResult<Object> addComplexSubEvnet(KnowledgeComplexSubEvnet subEvnet) {
        if (ObjectUtils.isEmpty(subEvnet)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            String subEventName = subEvnet.getSubeventName()+"-" + subEvnet.getAttrName();
            if (subEvnet.getAttributeRelation().equals("0")) {
                subEventName += "<" + subEvnet.getRelationValue();
            } else if (subEvnet.getAttributeRelation().equals("1")) {
                subEventName += "<=" + subEvnet.getRelationValue();
            } else if (subEvnet.getAttributeRelation().equals("2")) {
                subEventName += "=" + subEvnet.getRelationValue();
            } else if (subEvnet.getAttributeRelation().equals("3")) {
                subEventName += ">=" + subEvnet.getRelationValue();
            } else if (subEvnet.getAttributeRelation().equals("4")) {
                subEventName += ">" + subEvnet.getRelationValue();
            } else if (subEvnet.getAttributeRelation().equals("5")) {
                subEventName += "between" + subEvnet.getRelationValue();
            } else if (subEvnet.getAttributeRelation().equals("6")) {
                subEventName += "!=" + subEvnet.getRelationValue();
            } else if (subEvnet.getAttributeRelation().equals("7")) {
                subEventName += "in" + subEvnet.getRelationValue();
            }
            subEvnet.setSubeventName(subEventName);
            KnowledgeComplexSubEvnet save = knowledgeComplexSubEventRepository.save(subEvnet);
            if (subEvnet.getId() == null) {
                KnowledgeComplexSubeventRelation k = new KnowledgeComplexSubeventRelation();
                k.setComplexEventId(save.getComplexEventId());
                Date date = new Date();
                k.setInsertTime(date.toString());
                k.setRelationIdName(save.getId() + "");

                k.setRelationName(save.getSubeventName());
                k.setType(0);
                knowledgeComplexSubeventRelationRepository.save(k);
            } else {
                KnowledgeComplexSubeventRelation subeventRelation = knowledgeComplexSubeventRelationRepository.findByRelationIdName(subEvnet.getId() + "");
                subeventRelation.setRelationName(subEvnet.getSubeventName());
                knowledgeComplexSubeventRelationRepository.save(subeventRelation);
            }
            return new JsonResult<>(save);
        }
    }

    public JsonResult<Object> getAllSubEvent(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(knowledgeComplexSubEventRepository.getAllByComplexEventId(complexEventId));
        }
    }

    @Transactional
    public JsonResult<Object> deleteComplexEvent(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            complexEventRepository.deleteById(complexEventId);
            knowledgeComplexSubEventRepository.deleteAllByComplexEventId(complexEventId);
            knowledgeComplexTargetRepository.deleteAllByComplexEventId(complexEventId);
            knowledgeComplexSubeventRelationRepository.deleteAllByComplexEventId(complexEventId);
            knowledgeComplexTargetRelationRepository.deleteAllByComplexEventId(complexEventId);
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

    public JsonResult<Object> addMetaEventRelation(Long left, Long right, Integer lr, Long complexId) {
        if (ObjectUtils.isEmpty(left) || ObjectUtils.isEmpty(right) || ObjectUtils.isEmpty(lr) || ObjectUtils.isEmpty(complexId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            KnowledgeComplexSubeventRelation l = knowledgeComplexSubeventRelationRepository.getOne(left);
            KnowledgeComplexSubeventRelation r = knowledgeComplexSubeventRelationRepository.getOne(right);
            KnowledgeComplexSubeventRelation k = new KnowledgeComplexSubeventRelation();
            KnowledgeComplexEvent complexEvent = complexEventRepository.getOne(complexId);
            StringBuilder sb = new StringBuilder();
            sb.append('(').append(l.getRelationName()).append(')');
            if (lr == 1) {
                sb.append('&');
            } else if (lr == 2) {
                sb.append("|");
            }
            sb.append('(').append(r.getRelationName()).append(')');
            k.setType(1);
            k.setRelationName(sb.toString());
            sb.delete(0,sb.length());
            sb.append('(').append(l.getRelationIdName()).append(')');
            if (lr == 1) {
                sb.append('&');
            } else if (lr == 2) {
                sb.append("|");
            }
            sb.append('(').append(r.getRelationIdName()).append(')');
            k.setRelationIdName(sb.toString());
            k.setInsertTime(new Date().toString());
            k.setComplexEventId(complexId);
            KnowledgeComplexSubeventRelation subeventRelation = knowledgeComplexSubeventRelationRepository.save(k);
            complexEvent.setRelation(subeventRelation.getRelationName());
            complexEvent.setIdRelation(subeventRelation.getRelationIdName());
            complexEventRepository.save(complexEvent);
            return new JsonResult<>();
        }
    }

    public JsonResult<Object> addComplexTarget(KnowledgeComplexTarget knowledgeComplexTarget) {
        if (ObjectUtils.isEmpty(knowledgeComplexTarget)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            String targetName = knowledgeComplexTarget.getSubeventName()+"-" + knowledgeComplexTarget.getAttrName();
            if (knowledgeComplexTarget.getAttributeRelation().equals("0")) {
                targetName += "<" + knowledgeComplexTarget.getRelationValue();
            } else if (knowledgeComplexTarget.getAttributeRelation().equals("1")) {
                targetName += "<=" + knowledgeComplexTarget.getRelationValue();
            } else if (knowledgeComplexTarget.getAttributeRelation().equals("2")) {
                targetName += "=" + knowledgeComplexTarget.getRelationValue();
            } else if (knowledgeComplexTarget.getAttributeRelation().equals("3")) {
                targetName += ">=" + knowledgeComplexTarget.getRelationValue();
            } else if (knowledgeComplexTarget.getAttributeRelation().equals("4")) {
                targetName += ">" + knowledgeComplexTarget.getRelationValue();
            } else if (knowledgeComplexTarget.getAttributeRelation().equals("5")) {
                targetName += "between" + knowledgeComplexTarget.getRelationValue();
            } else if (knowledgeComplexTarget.getAttributeRelation().equals("6")) {
                targetName += "!=" + knowledgeComplexTarget.getRelationValue();
            } else if (knowledgeComplexTarget.getAttributeRelation().equals("7")) {
                targetName += "in" + knowledgeComplexTarget.getRelationValue();
            }
            knowledgeComplexTarget.setSubeventName(targetName);
            KnowledgeComplexTarget save = knowledgeComplexTargetRepository.save(knowledgeComplexTarget);
            if (knowledgeComplexTarget.getId() == null) {
                KnowledgeComplexTargetRelation k = new KnowledgeComplexTargetRelation();
                k.setComplexEventId(save.getComplexEventId());
                Date date = new Date();
                k.setInsertTime(date.toString());
                k.setTargetIdName(save.getId() + "");
                k.setRelationName(save.getSubeventName());
                k.setType(0);
                knowledgeComplexTargetRelationRepository.save(k);
            } else {
                KnowledgeComplexTargetRelation targetRelation = knowledgeComplexTargetRelationRepository.findByTargetIdName(knowledgeComplexTarget.getId() + "");
                targetRelation.setRelationName(knowledgeComplexTarget.getSubeventName());
                knowledgeComplexTargetRelationRepository.save(targetRelation);
            }
            return new JsonResult<>();
        }
    }

    public JsonResult<Object> getAllTarget(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {

            return new JsonResult<>(knowledgeComplexTargetRepository.getAllByComplexEventId(complexEventId));
        }
    }

    public JsonResult<Object> addTargetRelation(Long left, Long right, Integer lr, Long complexId) {
        if (ObjectUtils.isEmpty(left) || ObjectUtils.isEmpty(right) || ObjectUtils.isEmpty(lr) || ObjectUtils.isEmpty(complexId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            KnowledgeComplexTargetRelation l = knowledgeComplexTargetRelationRepository.getOne(left);
            KnowledgeComplexTargetRelation r = knowledgeComplexTargetRelationRepository.getOne(right);
            KnowledgeComplexTargetRelation k = new KnowledgeComplexTargetRelation();
            KnowledgeComplexEvent complexEvent = complexEventRepository.getOne(complexId);
            StringBuilder sb = new StringBuilder();
            sb.append('(').append(l.getRelationName()).append(')');
            if (lr == 1) {
                sb.append('&');
            } else if (lr == 2) {
                sb.append("|");
            }
            sb.append('(').append(r.getRelationName()).append(')');
            k.setType(1);
            k.setRelationName(sb.toString());
            sb.delete(0,sb.length());
            sb.append('(').append(l.getTargetIdName()).append(')');
            if (lr == 1) {
                sb.append('&');
            } else if (lr == 2) {
                sb.append("|");
            }
            sb.append('(').append(r.getTargetIdName()).append(')');
            k.setTargetIdName(sb.toString());
            k.setInsertTime(new Date().toString());
            k.setComplexEventId(complexId);
            KnowledgeComplexTargetRelation targetRelation = knowledgeComplexTargetRelationRepository.save(k);
            complexEvent.setTargetRelation(targetRelation.getRelationName());
            complexEvent.setIdTargetRelation(targetRelation.getTargetIdName());
            complexEventRepository.save(complexEvent);
            return new JsonResult<>();
        }
    }

    public JsonResult<Object> getAllSubEventRelation(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(knowledgeComplexSubeventRelationRepository.getAllByComplexEventId(complexEventId));
        }
    }

    public JsonResult<Object> getAllTargetRelation(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            return new JsonResult<>(knowledgeComplexTargetRelationRepository.getAllByComplexEventId(complexEventId));
        }
    }

    public JsonResult<Object> deleteComplexTarget(Long complexTargetId) {
        if (ObjectUtils.isEmpty(complexTargetId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            knowledgeComplexTargetRepository.deleteById(complexTargetId);
            return new JsonResult<>();
        }
    }
    @Transactional
    public JsonResult<Object> deleteSubEventRelation(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            knowledgeComplexSubeventRelationRepository.deleteAllByType(1);
            KnowledgeComplexEvent one = complexEventRepository.getOne(complexEventId);
            one.setRelation(null);
            complexEventRepository.save(one);
            return new JsonResult<>();
        }
    }
    @Transactional
    public JsonResult<Object> deleteTargetRelation(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            knowledgeComplexTargetRelationRepository.deleteAllByType(1);
            KnowledgeComplexEvent one = complexEventRepository.getOne(complexEventId);
            one.setTargetRelation(null);
            complexEventRepository.save(one);
            return new JsonResult<>();
        }
    }

    /**
     * 推理
     * */
    public JsonResult<Object> deduce(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            deduceContext.begainDeduce(complexEventId);
            return new JsonResult<>(complexEventId);
        }
    }

    public JsonResult<Object> stopDeduce(Long complexEventId) {
        if (ObjectUtils.isEmpty(complexEventId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        } else {
            deduceContext.stopDeduce(complexEventId);
            return new JsonResult<>(complexEventId);
        }
    }

    public JsonResult<Object> getDeduceResult() {
        List<KnowledgeComplexEvent> result = new ArrayList<>();
        KnowledgeComplexEvent complexEvent = null;
        while ((complexEvent = DeduceContext.complexEventFounded.poll()) != null) {
            result.add(complexEvent);
        }
        return new JsonResult<>(result);
    }
    /**
     * 获取所有原子事件
     * */
//    public JsonResult<Object> getAllMetaEvent(Long complexEventId) {
//        if (ObjectUtils.isEmpty(complexEventId)) {
//            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
//        }
//        List<KnowledgeComplexSubEvnet> allByComplexEventId = knowledgeComplexSubEventRepository.getAllByComplexEventId(complexEventId);
//        List<Long> metaEventIds = allByComplexEventId.stream().map(event -> event.getSubeventId()).collect(Collectors.toList());
//
//    }
}
