package com.rcd.iotsubsys.util;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2019-12-31 10:28
 */
public class DirectoryUtil {
    private static final Logger log = LoggerFactory.getLogger(DirectoryUtil.class);

    private static final String DIRECTORY_ROOT = "iot";

    private static Jedis jedis = new Jedis("localhost", 6379);

    private static boolean addDirNode(String path) {

        String jsonString = jedis.get(DIRECTORY_ROOT);

        DirectoryNode root;
        try{
            if (jsonString == null) {
                root = new DirectoryNode(DIRECTORY_ROOT);
                jedis.set(DIRECTORY_ROOT, SerializeUtil.objectToJson(root));
                jsonString = jedis.get(DIRECTORY_ROOT);
            }
            root = (DirectoryNode) SerializeUtil.jsonToObject(jsonString, DirectoryNode.class);
            if (root != null) {
                String[] paths = path.split("/");
                insertIntoDir(root, paths, 0);
            }

            String newDirString = SerializeUtil.objectToJson(root);

            jedis.set(DIRECTORY_ROOT, newDirString);
        } catch (IOException e) {
            log.error("添加目录结点失败");
            return false;
        }
        return true;
    }

    private static void insertIntoDir(DirectoryNode root, String[] paths, int curIdx) {
        if (curIdx >= paths.length - 1) return;
        if (curIdx == paths.length - 2) {
            root.child.add(new DirectoryNode(paths[curIdx+1]));
            return;
        } else {
            if(paths[curIdx].trim().equals("")) insertIntoDir(root, paths, curIdx+1);
            else{
                String curNodeName = paths[curIdx];
                if(curNodeName.equals(root.name)){
                    List<DirectoryNode> childs = root.child;
                    for (DirectoryNode d : childs) {
                        if (d.name.equals(curNodeName)) {
                            insertIntoDir(d, paths, curIdx + 1);
                        }
                    }
                }

            }
        }
    }

    private static void showAllDirectoryFromRedis() throws IOException {
        String allKeys = jedis.get(DIRECTORY_ROOT);
        DirectoryNode root = (DirectoryNode) SerializeUtil.jsonToObject(allKeys, DirectoryNode.class);
        showAllDirectory(root);
    }

    private static void showAllDirectory(DirectoryNode root) throws IOException {
        showHelper(root, 0);
    }

    private static void showHelper(DirectoryNode root, int deepth) {
        if (root == null) return;
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < deepth; ++i) sb.append("---");
            sb.append(root.name);
            System.out.println(sb.toString());
            List<DirectoryNode> child = root.child;
            for (DirectoryNode node : child) {
                if (node != null) showHelper(node, deepth + 1);
            }
        }
    }

    private static void clear(){
        jedis.del(DIRECTORY_ROOT);
    }
    static class DirectoryNode {
        String name;
        Object attachment;
        @JsonUnwrapped
        List<DirectoryNode> child;

        DirectoryNode() {
        }

        DirectoryNode(String name) {
            this.name = name;
            this.child = new ArrayList<>();
        }

        DirectoryNode(String name, Object attachment) {
            this.name = name;
            this.attachment = attachment;
            this.child = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAttachment() {
            return attachment;
        }

        public void setAttachment(Object attachment) {
            this.attachment = attachment;
        }

        public List<DirectoryNode> getChild() {
            return child;
        }

        public void setChild(List<DirectoryNode> child) {
            this.child = child;
        }
    }

    public static void main(String[] args) throws IOException {
        addDirNode("/bupt");
        //showAllDirectory();
        DirectoryNode node = new DirectoryNode("hello");
        node.child.add(new DirectoryNode("world"));
        node.child.add(new DirectoryNode("world1"));
        node.child.get(0).child.add(new DirectoryNode("nihao"));
        node.child.get(1).child.add(new DirectoryNode("nihao2"));
        String s = SerializeUtil.objectToJson(node);
        System.out.println(s);
        DirectoryNode root = (DirectoryNode) SerializeUtil.jsonToObject(s,DirectoryNode.class);
        showAllDirectory(root);
        clear();
        String json = jedis.get(DIRECTORY_ROOT);
        System.out.println(json);
        addDirNode("/iot/hello/world1");
        json = jedis.get(DIRECTORY_ROOT);
        System.out.println(json);
        addDirNode("/iot/hello/world2");
        json = jedis.get(DIRECTORY_ROOT);
        System.out.println(json);
        addDirNode("/iot/father/son1");
        json = jedis.get(DIRECTORY_ROOT);
        System.out.println(json);
        addDirNode("/iot/father/son2");
        //showAllDirectoryFromRedis();
        json = jedis.get(DIRECTORY_ROOT);
        System.out.println(json);
        System.in.read();
    }
}
