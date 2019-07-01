package com.rcd.iotsubsys.domain.event;

import javax.persistence.*;

/**
 * 复杂事件属性表
 */
@Entity
@Table(name = "attribute_relation_complex_event")
public class AttributeRelationComplexEvent {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应复杂事件id
     */
    @Column(name = "complex_event_id", nullable = false)
    private String complexEventId;

    /**
     * type 0:代表所选原子事件；1:代表属性
     */
    @Column(name = "type", nullable = false)
    private String type;

    /**
     * 属性名称
     */
    @Column(name = "attribute_name")
    private String attributeName;

    /**
     * 属性值
     */
    @Column(name = "attribute_value")
    private String attributeValue;

    /**
     * 所选原子事件id
     */
    @Column(name = "meta_event_id")
    private String metaEventId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplexEventId() {
        return complexEventId;
    }

    public void setComplexEventId(String complexEventId) {
        this.complexEventId = complexEventId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getMetaEventId() {
        return metaEventId;
    }

    public void setMetaEventId(String metaEventId) {
        this.metaEventId = metaEventId;
    }
}
