package com.rcd.iotsubsys.common.directory;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-22 17:15
 */
public enum DirectoryNodeOwner {

    KNOWLEDGE_CLASS("knowledge_class"),
    KNOWLEDGE("knowledge"),
    KNOWLEDGE_INSTANCE("knowledge_instance");

    DirectoryNodeOwner(String owner) {
        this.owner = owner;
    }

    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }}
