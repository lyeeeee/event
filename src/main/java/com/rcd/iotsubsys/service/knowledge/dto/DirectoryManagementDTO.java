package com.rcd.iotsubsys.service.knowledge.dto;

import com.rcd.iotsubsys.domain.knowledge.DirectoryManagement;

import java.util.LinkedList;
import java.util.List;

public class DirectoryManagementDTO {

    private Long id;
    /** 目录名称*/
    private String name;
    /** 目录type（领域:01、部门:02、元目录:03）*/
    private String type;
    /** 插入时间*/
    private String insertTime;

    private List<DirectoryManagement> child = new LinkedList<>();

    private DirectoryManagement parent;
    /**父领域、部门id*/
    private Long parentId;

    DirectoryManagementDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public List<DirectoryManagement> getChild() {
        return child;
    }

    public void setChild(List<DirectoryManagement> child) {
        this.child = child;
    }

    public DirectoryManagement getParent() {
        return parent;
    }

    public void setParent(DirectoryManagement parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
