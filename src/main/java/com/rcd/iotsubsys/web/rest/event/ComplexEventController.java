package com.rcd.iotsubsys.web.rest.event;

import com.alibaba.fastjson.JSONObject;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexEvent;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexSubEvnet;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexTarget;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.service.event.ComplexEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-04 18:16
 */
@RestController
@RequestMapping("api/complexevent")
public class ComplexEventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComplexEventController.class);

    @Autowired
    private ComplexEventService complexEventService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult<Object> add(@RequestBody KnowledgeComplexEvent knowledgeComplexEvent) throws IOException {
        LOGGER.info("add complexevent with param:{}", JSONObject.toJSONString(knowledgeComplexEvent));
        return complexEventService.addComplexEvent(knowledgeComplexEvent);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public JsonResult<Object> getAll(@RequestParam(required = false) String name) throws IOException {
        LOGGER.info("get all complexevent");
        return complexEventService.getAllComplexEvent(name);
    }

    @RequestMapping(value = "/addSubEvent", method = RequestMethod.POST)
    public JsonResult<Object> add(@RequestBody KnowledgeComplexSubEvnet knowledgeComplexSubEvnet) throws IOException {
        LOGGER.info("add SubEvent with param:{}", JSONObject.toJSONString(knowledgeComplexSubEvnet));
        return complexEventService.addComplexSubEvnet(knowledgeComplexSubEvnet);
    }

    @RequestMapping(value = "/getAllSubEvent", method = RequestMethod.GET)
    public JsonResult<Object> add(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("getAllSubEvent with param:{}", complexEventId);
        return complexEventService.getAllSubEvent(complexEventId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public JsonResult<Object> delete(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("delete with param:{}", complexEventId);
        return complexEventService.deleteComplexEvent(complexEventId);
    }

    @RequestMapping(value = "/deleteSubEvent", method = RequestMethod.DELETE)
    public JsonResult<Object> deleteSubEvent(@RequestParam Long complexSubEventId) throws IOException {
        LOGGER.info("deleteSubEvent with param:{}", complexSubEventId);
        return complexEventService.deleteComplexSubEvent(complexSubEventId);
    }

    @RequestMapping(value = "/addMetaEventRelation", method = RequestMethod.GET)
    public JsonResult<Object> addMetaEventRelation(@RequestParam Long complexEventId,
                                                   @RequestParam String relation) throws IOException {
        LOGGER.info("addMetaEventRelation with complexEventId:{}, relation:{}", complexEventId, relation);
        return complexEventService.addMetaEventRelation(complexEventId, relation);
    }

    @RequestMapping(value = "/addTarget", method = RequestMethod.POST)
    public JsonResult<Object> addTarget(@RequestBody KnowledgeComplexTarget knowledgeComplexTarget) throws IOException {
        LOGGER.info("add target with param:{}", JSONObject.toJSONString(knowledgeComplexTarget));
        return complexEventService.addComplexTarget(knowledgeComplexTarget);
    }

    @RequestMapping(value = "/getAllTarget", method = RequestMethod.GET)
    public JsonResult<Object> getAllTarget(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("getAllTarget with param:{}", complexEventId);
        return complexEventService.getAllTarget(complexEventId);
    }

    @RequestMapping(value = "/addTargetRelation", method = RequestMethod.GET)
    public JsonResult<Object> addTargetRelation(@RequestParam Long complexEventId,
                                                   @RequestParam String relation) throws IOException {
        LOGGER.info("addTargetRelation with complexEventId:{}, relation:{}", complexEventId, relation);
        return complexEventService.addTargetRelation(complexEventId, relation);
    }
}
