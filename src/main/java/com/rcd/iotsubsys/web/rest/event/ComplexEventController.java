package com.rcd.iotsubsys.web.rest.event;

import com.alibaba.fastjson.JSONObject;
import com.rcd.iotsubsys.domain.event.FolumaKnowledge;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexEvent;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexSubEvnet;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexTarget;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeSelectedFormula;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.service.event.ComplexEventService;
import com.rcd.iotsubsys.service.knowledge.dto.KnowledgeRangeDTO;
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
    public JsonResult<Object> getAllSubEvent(@RequestParam Long complexEventId) throws IOException {
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

    /**
     * 新增原子事件关系
     * */
    @RequestMapping(value = "/addMetaEventRelation", method = RequestMethod.GET)
    public JsonResult<Object> addMetaEventRelation(@RequestParam Long left, @RequestParam Long right,
                                                   @RequestParam Integer lr, @RequestParam Long complexId) throws IOException {
        LOGGER.info("addMetaEventRelation with left:{}, right:{}, lr:{}, complexId:{}", left, right,lr, complexId);
        return complexEventService.addMetaEventRelation(left, right,lr,complexId);
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
    public JsonResult<Object> addTargetRelation(@RequestParam Long left, @RequestParam Long right,
                                                @RequestParam Integer lr, @RequestParam Long complexId) throws IOException {
        LOGGER.info("addTargetRelation with left:{}, right:{}, lr:{}, complexId:{}", left, right,lr, complexId);
        return complexEventService.addTargetRelation(left, right,lr, complexId);
    }

    @RequestMapping(value = "/getAllSubEventRelation", method = RequestMethod.GET)
    public JsonResult<Object> getAllSubEventRelation(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("getAllSubEventRelation with param:{}", complexEventId);
        return complexEventService.getAllSubEventRelation(complexEventId);
    }
    @RequestMapping(value = "/getAllTargetRelation", method = RequestMethod.GET)
    public JsonResult<Object> getAllTargetRelation(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("getAllTargetRelation with param:{}", complexEventId);
        return complexEventService.getAllTargetRelation(complexEventId);
    }

    @RequestMapping(value = "/deleteTarget", method = RequestMethod.DELETE)
    public JsonResult<Object> deleteTarget(@RequestParam Long complexTargetId) throws IOException {
        LOGGER.info("deleteTarget with param:{}", complexTargetId);
        return complexEventService.deleteComplexTarget(complexTargetId);
    }

    @RequestMapping(value = "/deleteSubEventRelation", method = RequestMethod.DELETE)
    public JsonResult<Object> deleteSubEventRelation(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("deleteTarget with param:{}", complexEventId);
        return complexEventService.deleteSubEventRelation(complexEventId);
    }

    @RequestMapping(value = "/deleteTargetRelation", method = RequestMethod.DELETE)
    public JsonResult<Object> deleteTargetRelation(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("deleteTarget with param:{}", complexEventId);
        return complexEventService.deleteTargetRelation(complexEventId);
    }

    @RequestMapping(value = "/deduce", method = RequestMethod.GET)
    public JsonResult<Object> deduce(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("deduce with complexEventId:{}", complexEventId);
        return complexEventService.deduce(complexEventId);
    }

    @RequestMapping(value = "/stopDeduce", method = RequestMethod.GET)
    public JsonResult<Object> stopDeduce(@RequestParam Long complexEventId) throws IOException {
        LOGGER.info("stop deduce with complexEventId:{}", complexEventId);
        return complexEventService.stopDeduce(complexEventId);
    }

    @RequestMapping(value = "/getDeduceResult", method = RequestMethod.GET)
    public JsonResult<Object> getDeduceResult() {
        LOGGER.info("getDeduceResult....");
        return complexEventService.getDeduceResult();
    }

    @RequestMapping(value = "/getKnowledgeByComplexId", method = RequestMethod.GET)
    public JsonResult<Object> getKnowledgeByComplexId(@RequestParam Long complexEventId) {
        LOGGER.info("getKnowledgeByComplexId....");
        return complexEventService.getKnowledgeByComplexId(complexEventId);
    }

    @RequestMapping(value = "/saveKnowledgeForEvent", method = RequestMethod.GET)
    public JsonResult<Object> saveKnowledgeForEvent(@RequestParam Long complexEventId, @RequestParam(required = false) Long[] selectedIds) {
        if (selectedIds == null) selectedIds = new Long[]{};
        return complexEventService.saveKnowledgeForEvent(complexEventId, selectedIds);
    }

    @RequestMapping(value = "/addFolumaKnowledge", method = RequestMethod.POST)
    public JsonResult<Object> addFolumaKnowledge(@RequestBody FolumaKnowledge knowledgeFoluma) {
        LOGGER.info(JSONObject.toJSONString(knowledgeFoluma));
        return complexEventService.addFolumaKnowledge(knowledgeFoluma);
    }

    @RequestMapping(value = "/saveKnowledgeByRange", method = RequestMethod.POST)
    public JsonResult<Object> saveKnowledgeByRange(@RequestBody(required = false)KnowledgeRangeDTO knowledgeRangeDTO) {
        return complexEventService.saveKnowledgeByRange(knowledgeRangeDTO.getComplexId()
            , knowledgeRangeDTO.getField()
            , knowledgeRangeDTO.getDepartment()
            , knowledgeRangeDTO.getMetaDir()
            , knowledgeRangeDTO.getS()
            , knowledgeRangeDTO.getP()
            , knowledgeRangeDTO.getO());
    }

    @RequestMapping(value = "/getFomulaByType", method = RequestMethod.GET)
    public JsonResult<Object> getFomulaByType(@RequestParam Integer type) {
        return complexEventService.getFomulaByType(type);
    }

    @RequestMapping(value = "/getFomulaByComplexId", method = RequestMethod.GET)
    public JsonResult<Object> getFomulaByComplexId(@RequestParam Long complexId) {
        return complexEventService.getFomulaByComplexId(complexId);
    }
    @RequestMapping(value = "/addSelectedFoluma", method = RequestMethod.POST)
    public JsonResult<Object> addSelectedFoluma(@RequestBody(required = false) KnowledgeSelectedFormula knowledgeSelectedFormula) {
        return complexEventService.addSelectedFoluma(knowledgeSelectedFormula);
    }

    @RequestMapping(value = "/getAllAlarm", method = RequestMethod.GET)
    public JsonResult<Object> getAllAlarm(String complexEvent) {
        return complexEventService.getAllAlarm(complexEvent);
    }
}
