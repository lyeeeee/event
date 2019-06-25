package com.rcd.iotsubsys.domain.event;

import javax.persistence.*;

/**
 * 原子事件定义的原子事件与设备属性的对应关系表
 */
@Entity
@Table(name = "attribute_relation_meta_event")
public class AttributeRelationMetaEvent {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应原子事件id
     */
    @Column(name = "meta_event_id", nullable = false)
    private String metaEventId;

    /**
     * 设备
     */
    @Column(name = "equipment", nullable = false)
    private String equipment;

    /**
     * 属性
     */
    @Column(name = "attribute",columnDefinition="varchar(500)")
    private String attribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetaEventId() {
        return metaEventId;
    }

    public void setMetaEventId(String metaEventId) {
        this.metaEventId = metaEventId;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
