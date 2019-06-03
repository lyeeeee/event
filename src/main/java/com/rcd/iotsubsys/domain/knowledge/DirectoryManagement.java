package com.rcd.iotsubsys.domain.knowledge;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 目录表（领域、部门、元目录）
 */
@Entity
@Table(name = "knowledge_directory_management")
public class DirectoryManagement {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 目录名称
     */
    @Column(name = "name", nullable = false, length = 15)
    private String name;

    /**
     * 目录type（领域:01、部门:02、元目录:03）
     */
    @Column(name = "type", nullable = false)
    private String type;
//
//    /**
//     * 父目录id
//     */
//    @Column(name = "parent_id")
//    private String parentId;

    /**
     * 插入时间
     */
    @Column(name = "insert_time", nullable = false)
    private String insertTime;

    @OneToMany(mappedBy = "parent")
//    @JsonIgnore
    private List<DirectoryManagement> child = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "id")
    private DirectoryManagement parent;


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
}
