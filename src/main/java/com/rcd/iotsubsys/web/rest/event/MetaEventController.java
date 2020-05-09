package com.rcd.iotsubsys.web.rest.event;

import com.alibaba.fastjson.JSONObject;
import com.rcd.iotsubsys.domain.event.MetaEventAttrRelation;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.service.event.MetaEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-24 18:13
 */
@RestController
@RequestMapping("api/metaevent")
public class MetaEventController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaEventController.class);

    @Autowired
    private MetaEventService metaEventService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult<Object> add(@RequestBody KnowledgeMetaEvent knowledgeMetaEvent) throws IOException {
        LOGGER.info("add metaevent with param:{}", JSONObject.toJSONString(knowledgeMetaEvent));
        return metaEventService.addMetaEvent(knowledgeMetaEvent);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public JsonResult<Object> getAll(@RequestParam(required = false) String name) throws IOException {
        LOGGER.info("get all metaevent");
        return metaEventService.getAllMetaEvent(name);
    }

    @RequestMapping(value = "/getAllByIds", method = RequestMethod.GET)
    public JsonResult<Object> getAllByIds(@RequestParam(required = false) List<Long> ids) throws IOException {
        LOGGER.info("get all metaevent by ids:{}", JSONObject.toJSONString(ids));
        return metaEventService.getMetaEventByIds(ids);
    }


    @RequestMapping(value = "/getRelation", method = RequestMethod.GET)
    public JsonResult<Object> getRelationMap(@RequestParam(required = false) Long relationId) throws IOException {
        LOGGER.info("getRelationMap with relationId:{}", relationId);
        return metaEventService.getRelationMap(relationId);
    }

    @RequestMapping(value = "/addRelation", method = RequestMethod.POST)
    public JsonResult<Object> addRelation(@RequestBody MetaEventAttrRelation metaEventAttrRelation) throws IOException {
        LOGGER.info("addRelation with param:{}", JSONObject.toJSONString(metaEventAttrRelation));
        return metaEventService.addMetaEventRelation(metaEventAttrRelation);
    }

    @RequestMapping(value = "/getAllRelation", method = RequestMethod.GET)
    public JsonResult<Object> getAllRelation(@RequestParam(required = false) Long metaEventId) throws IOException {
        LOGGER.info("getAllRelation with metaEventId:{}", metaEventId);
        return metaEventService.getAllRelation(metaEventId);
    }

    @RequestMapping(value = "/deleteRelation", method = RequestMethod.DELETE)
    public JsonResult<Object> deleteRelation(@RequestParam(required = false) Long relationId) throws IOException {
        LOGGER.info("deleteRelation with relationId:{}", relationId);
        return metaEventService.deleteRelation(relationId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public JsonResult<Object> delete(@RequestParam(required = false) Long metaEventId) throws IOException {
        LOGGER.info("delete with metaEventId:{}", metaEventId);
        return metaEventService.deleteMetaEvent(metaEventId);
    }
}
