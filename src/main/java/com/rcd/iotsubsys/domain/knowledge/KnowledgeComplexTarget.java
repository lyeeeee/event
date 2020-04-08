package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-08 10:16
 */
@Entity
@Table(name = "knowledge_complex_target")
@Proxy(lazy = false)
public class KnowledgeComplexTarget {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


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

    /**
     * 时间窗口
     */
    @Column(name = "time_window")
    private String timeWindow;

    /**
     * 长度窗口
     */
    @Column(name = "len_window")
    private Long lenWindow;

    public String getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(String timeWindow) {
        this.timeWindow = timeWindow;
    }

    public Long getLenWindow() {
        return lenWindow;
    }

    public void setLenWindow(Long lenWindow) {
        this.lenWindow = lenWindow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
