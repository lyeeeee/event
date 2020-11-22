package com.rcd.iotsubsys.service.directory;

import com.rcd.iotsubsys.domain.directory.DirectoryNode;
import com.rcd.iotsubsys.dto.directory.DirectoryDTO;
import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.factory.DirectoryFactory;
import com.rcd.iotsubsys.repository.directory.DirectoryRepository;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-04 13:51
 */
@Service
public class KnowledgeDirectoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeDirectoryService.class);
    @Autowired
    DirectoryRepository directoryRepository;

    public JsonResult<Object> addOrUpdateDirectoryNode(DirectoryNode directoryNode) {
        DirectoryNode node = directoryRepository.save(directoryNode);
        if (node == null) {
            return new JsonResult<>(ResponseCode.IOT_DIRECTORY_SAVE_OR_UPDATE_ERROR);
        }
        return new JsonResult<>(node);
    }

    public JsonResult<Object> deleteDirectoryNode(Long nodeId, Boolean cascade) {
        DirectoryNode node = directoryRepository.getOne(nodeId);

        List<DirectoryNode> childs = directoryRepository.findAllByParentId(nodeId);

        directoryRepository.delete(node);
        if (CollectionUtils.isEmpty(childs)) {
            return new JsonResult<>();
        }
        if (cascade) {
           for (int i = 0;i < childs.size(); ++i) {
               deleteDirectoryNode(childs.get(i).getId(), true);
           }
        } else {
            return new JsonResult<>(ResponseCode.DIRECTORY_NODE_HAS_CHILD);
        }
        return new JsonResult<>();
    }

    public JsonResult<Object> getDirectoryNodeWithID(Long nodeId) {
        DirectoryNode node = directoryRepository.getOne(nodeId);
        if (ObjectUtils.isEmpty(node)) {
            return new JsonResult<>(ResponseCode.DIRECTORY_NODE_NOT_EXIST);
        }
        return new JsonResult<>(node);
    }

    public JsonResult<Object> getDirectoryNodeWithIDs(List<Long> ids) {
        List<DirectoryNode> node = directoryRepository.getAllByIdIn(ids);
        if (ObjectUtils.isEmpty(node)) {
            return new JsonResult<>(ResponseCode.DIRECTORY_NODE_NOT_EXIST);
        }
        return new JsonResult<>(node);
    }
    public JsonResult<Object> getDirectoryNodeWithName(String nodeName) {
        List<DirectoryNode> node = directoryRepository.getAllByValue(nodeName);
        if (ObjectUtils.isEmpty(node)) {
            return new JsonResult<>(ResponseCode.DIRECTORY_NODE_NOT_EXIST);
        }
        return new JsonResult<>(node);
    }


    public JsonResult<Object> getAllDirectoryWithOwner(List<String> owner) {
        List<DirectoryNode> allNodes = new ArrayList<>();
        for (String s : owner) {
            allNodes.addAll(directoryRepository.findAllByOwner(s));
        }
        LOGGER.info("get all nodes, list size:{}", allNodes.size());
        DirectoryDTO directoryDTO = DirectoryFactory.convertNodeList(allNodes);
        return new JsonResult<>(directoryDTO);
    }

    public List<DirectoryNode> getChildDirNode(String nodeName) {
        if (StringUtils.isEmpty(nodeName)) return new ArrayList<>();
        List<DirectoryNode> allByValue = directoryRepository.findAllByValue(nodeName);
        Set<Long> parentIds = allByValue.stream().map(DirectoryNode::getId).collect(Collectors.toSet());
        List<DirectoryNode> childs = directoryRepository.findAllByParentIdIn(parentIds);
        return childs;
    }
}
