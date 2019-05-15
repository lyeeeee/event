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
    private Long fieldId;

    /**
     * 所属领域name
     */
    @Column(name = "field_name", nullable = false, length = 15)
    private String fieldName;

    /**
     * 所属部门id
     */
    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    /**
     * 所属部门name
     */
    @Column(name = "department_name", nullable = false, length = 15)
    private String departmentName;

    /**
     * 所属元目录id
     */
    @Column(name = "meta_catalogue_id", nullable = false)
    private Long metaCatalogueId;

    /**
     * 所属元目录name
     */
    @Column(name = "meta_catalogue_name", nullable = false, length = 15)
    private String metaCatalogueName;

    /**
     * 对应graphDB数据库的id
     */
    @Column(name = "graphId", nullable = false)
    private String graphId;

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

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getField_name() {
        return fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getMetaCatalogueId() {
        return metaCatalogueId;
    }

    public void setMetaCatalogueId(Long metaCatalogueId) {
        this.metaCatalogueId = metaCatalogueId;
    }

    public String getMetaCatalogueName() {
        return metaCatalogueName;
    }

    public void setMetaCatalogueName(String metaCatalogueName) {
        this.metaCatalogueName = metaCatalogueName;
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getKnowledge_synopsis() {
        return knowledge_synopsis;
    }

    public void setKnowledge_synopsis(String knowledge_synopsis) {
        this.knowledge_synopsis = knowledge_synopsis;
    }
}
