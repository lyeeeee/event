package com.rcd.iotsubsys.service.knowledge.dto;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-11-22 21:26
 */
public class KnowledgeRangeDTO {
    private Long complexId;
    private String field;
    private String department;
    private String metaDir;
    private String s;
    private String p;
    private String o;

    public Long getComplexId() {
        return complexId;
    }

    public void setComplexId(Long complexId) {
        this.complexId = complexId;
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

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }
}
