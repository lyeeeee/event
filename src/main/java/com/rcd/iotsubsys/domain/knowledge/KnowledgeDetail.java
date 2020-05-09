package com.rcd.iotsubsys.domain.knowledge;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-29 10:29
 */
public class KnowledgeDetail {

    private String knowledgeName;

    private Long knowledgeId;

    private String field;

    private String department;

    private String metaDir;

    private Long fieldId;

    private Long departmentId;

    private Long metaDirId;

    private String knowledgeSynopsis;

    public String getKnowledgeSynopsis() {
        return knowledgeSynopsis;
    }

    public void setKnowledgeSynopsis(String knowledgeSynopsis) {
        this.knowledgeSynopsis = knowledgeSynopsis;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Long getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Long knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMetaDir() {
        return metaDir;
    }

    public void setMetaDir(String metaDir) {
        this.metaDir = metaDir;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getMetaDirId() {
        return metaDirId;
    }

    public void setMetaDirId(Long metaDirId) {
        this.metaDirId = metaDirId;
    }
}
