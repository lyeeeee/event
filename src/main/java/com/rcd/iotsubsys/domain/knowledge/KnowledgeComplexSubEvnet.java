package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-06 11:12
 */
@Entity
@Table(name = "knowledge_complex_subevent")
@Proxy(lazy = false)
public class KnowledgeComplexSubEvnet {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 子事件范围
     */
    @Column(name = "subevent_range", nullable = false)
    private String subeventRange;

    /**
     * 子事件id
     */
    @Column(name = "subevent_id", nullable = false)
    private Long subeventId;

    /**
     * 子事件名称
     */
    @Column(name = "subevent_name")
    private String subeventName;

    /**
     * 复杂事件id
     */
    @Column(name = "complex_event_id")
    private Long complexEventId;

    /**
     * 属性名称
     */
    @Column(name = "attr_name")
    private String attrName;


    /**
     * 小于。。小于等于。。
     */
    @Column(name = "attribute_relation")
    private String attributeRelation;

    /**
     * 约束值
     */
    @Column(name = "relation_value")
    private String relationValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubeventRange() {
        return subeventRange;
    }

    public void setSubeventRange(String subeventRange) {
        this.subeventRange = subeventRange;
    }

    public Long getSubeventId() {
        return subeventId;
    }

    public void setSubeventId(Long subeventId) {
        this.subeventId = subeventId;
    }

    public String getSubeventName() {
        return subeventName;
    }

    public void setSubeventName(String subeventName) {
        this.subeventName = subeventName;
    }

    public Long getComplexEventId() {
        return complexEventId;
    }

    public void setComplexEventId(Long complexEventId) {
        this.complexEventId = complexEventId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttributeRelation() {
        return attributeRelation;
    }

    public void setAttributeRelation(String attributeRelation) {
        this.attributeRelation = attributeRelation;
    }

    public String getRelationValue() {
        return relationValue;
    }

    public void setRelationValue(String relationValue) {
        this.relationValue = relationValue;
    }
}
