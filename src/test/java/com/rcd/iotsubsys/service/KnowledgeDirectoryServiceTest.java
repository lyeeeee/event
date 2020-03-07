package com.rcd.iotsubsys.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rcd.iotsubsys.IotKnowledgeSubSystemApp;
import com.rcd.iotsubsys.domain.directory.DirectoryNode;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.service.directory.KnowledgeDirectoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-04 18:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IotKnowledgeSubSystemApp.class)
@Transactional
public class KnowledgeDirectoryServiceTest {
    @Autowired
    KnowledgeDirectoryService knowledgeDirectoryService;

    @Test
    public void testAddOrUpdateDirectoryNode() {
        DirectoryNode node = new DirectoryNode();
        node.setnName("知识1-实例2");
        node.setParentId(10L);
        JsonResult<Object> result = knowledgeDirectoryService.addOrUpdateDirectoryNode(node);
        node = (DirectoryNode) result.getData();
        System.out.println(JSON.toJSONString(node));
    }

    @Test
    public void testDeleteDirectoryNode() {
        knowledgeDirectoryService.deleteDirectoryNode(13L,true);
    }

    @Test
    public void testGetDirectoryNodeWithID() {
        JsonResult<Object> result = knowledgeDirectoryService.getDirectoryNodeWithID(11L);
        DirectoryNode node = (DirectoryNode) result.getData();
        System.out.println(JSON.toJSONString(node));
    }
}
