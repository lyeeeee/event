package com.rcd.iotsubsys.domain.knowledge;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 知识表，存放知识名称、所属（领域、部门、元目录）、对应graphDB数据库的id 等信息
 */
@Entity
@Table(name = "knowledge_knowledge_info")
public class KnowledgeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 知识名称
     */
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    /**
     * 所属领域id
     */
    @Column(name = "field_id", nullable = false)
    private Long field_id;

    /**
     * 所属领域name
     */
    @Column(name = "field_name", nullable = false, length = 15)
    private String field_name;

    /**
     * 所属部门id
     */
    @Column(name = "department_id", nullable = false)
    private Long department_id;

    /**
     * 所属部门name
     */
    @Column(name = "department_name", nullable = false, length = 15)
    private String department_name;

    /**
     * 所属元目录id
     */
    @Column(name = "meta_catalogue_id", nullable = false)
    private Long meta_catalogue_id;

    /**
     * 所属元目录name
     */
    @Column(name = "meta_catalogue_name", nullable = false, length = 15)
    private String meta_catalogue_name;

    /**
     * 对应graphDB数据库的id
     */
    @Column(name = "graph_id", nullable = false)
    private String graph_id;

    /**
     * 知识简介
     */
    @Column(name = "knowledge_synopsis", length = 100)
    private String knowledge_synopsis;


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

    public Long getField_id() {
        return field_id;
    }

    public void setField_id(Long field_id) {
        this.field_id = field_id;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Long getMeta_catalogue_id() {
        return meta_catalogue_id;
    }

    public void setMeta_catalogue_id(Long meta_catalogue_id) {
        this.meta_catalogue_id = meta_catalogue_id;
    }

    public String getMeta_catalogue_name() {
        return meta_catalogue_name;
    }

    public void setMeta_catalogue_name(String meta_catalogue_name) {
        this.meta_catalogue_name = meta_catalogue_name;
    }

    public String getGraph_id() {
        return graph_id;
    }

    public void setGraph_id(String graph_id) {
        this.graph_id = graph_id;
    }

    public String getKnowledge_synopsis() {
        return knowledge_synopsis;
    }

    public void setKnowledge_synopsis(String knowledge_synopsis) {
        this.knowledge_synopsis = knowledge_synopsis;
    }
}
