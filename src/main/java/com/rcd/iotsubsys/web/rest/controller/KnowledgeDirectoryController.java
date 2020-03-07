package com.rcd.iotsubsys.web.rest.controller;

import com.alibaba.fastjson.JSONObject;
import com.rcd.iotsubsys.domain.directory.DirectoryNode;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.service.directory.KnowledgeDirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-04 13:38
 */
@RestController
@RequestMapping("api/directory")
public class KnowledgeDirectoryController {

    @Autowired
    private KnowledgeDirectoryService knowledgeDirectoryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeDirectoryController.class);

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult<Object> addOrUpdateDirectoryNode(@RequestBody DirectoryNode directoryNode) {
        LOGGER.info("addOrUpdateDirectoryNode with {}", JSONObject.toJSONString(directoryNode));
        JsonResult<Object> result = knowledgeDirectoryService.addOrUpdateDirectoryNode(directoryNode);
        return result;
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.DELETE)
    public JsonResult<Object> deleteDirectoryNode(@RequestParam Long nodeId, @RequestParam Boolean cascade) {
        LOGGER.info("deleteDirectoryNode with nodeId:{}", nodeId.longValue());
        if (ObjectUtils.isEmpty(nodeId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        }
        return knowledgeDirectoryService.deleteDirectoryNode(nodeId, cascade);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public JsonResult<Object> getDirectoryNode(@RequestParam Long nodeId) {
        LOGGER.info("getDirectoryNode with nodeId:{}", nodeId.longValue());
        if (ObjectUtils.isEmpty(nodeId)) {
            return new JsonResult<>(ResponseCode.PARAM_ILLAGLE_OR_NULL);
        }
        return knowledgeDirectoryService.getDirectoryNodeWithID(nodeId);
    }
}
