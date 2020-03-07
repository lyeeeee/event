package com.rcd.iotsubsys.factory;

import com.rcd.iotsubsys.domain.directory.DirectoryNode;
import com.rcd.iotsubsys.dto.directory.DirectoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-07 13:23
 */
public class DirectoryFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryFactory.class);

    public static DirectoryDTO convertNodeList(List<DirectoryNode> allNodes) {
        // parentId + children
        Map<Long, List<DirectoryNode>> nodes =  new HashMap<>();
        allNodes.forEach(node -> {
            List<DirectoryNode> child = nodes.get(node.getParentId());
            if (child == null) {
                child = new ArrayList<>();
                nodes.put(node.getParentId(), child);
            }
            child.add(node);
        });
        if (!nodes.containsKey(0L)) {
            LOGGER.error("不存在根节点");
            return new DirectoryDTO();
        }
        // 父节点为0的结点为根
        DirectoryNode cur = nodes.get(0L).get(0);
        DirectoryDTO directoryDTO = new DirectoryDTO();
        // 设置根节点为root
        directoryDTO.setCur(cur);
        Queue<Long> ids = new LinkedList<>();
        Map<Long, DirectoryDTO> map = new HashMap<>();
        map.put(cur.getId(), directoryDTO);
        // bfs， 初始化根节点的孩子进入队列
        ids.offer(cur.getId());
        while (!ids.isEmpty()) {
            int size = ids.size();
            while (size-- > 0) {
                Long id = ids.remove();
                DirectoryDTO tmp = map.get(id);
                if (nodes.get(id) != null) {
                    nodes.get(id).forEach( i-> {
                        ids.offer(i.getId());
                        DirectoryDTO d = new DirectoryDTO();
                        d.setCur(i);
                        tmp.getChild().add(d);
                        map.put(i.getId(), d);
                    });
                }
            }
        }
        return directoryDTO;
    }
}
