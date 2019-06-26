package com.rcd.iotsubsys.domain.event;

import javax.persistence.*;

/**
 * 原子事件定义的原子事件与设备属性的对应关系表
 *
 * 应只保存attribute_relation_equipment表的id，但为演示快速查询暂时先把所有需要字段都列出，
 * 后续修改要把相应字段去掉，加上对应的id
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
     * 知识图名称
     */
    @Column(name = "graph_name", nullable = false)
    private String graphName;
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

    /**
     * 系统
     */
    @Column(name = "iot_system", nullable = false)
    private String iotSystem;

    /**
     * 子站
     */
    @Column(name = "subsites", nullable = false)
    private String subsites;

    /**
     * 子系统
     */
    @Column(name = "subsystem", nullable = false)
    private String subsystem;

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

    public String getIotSystem() {
        return iotSystem;
    }

    public void setIotSystem(String iotSystem) {
        this.iotSystem = iotSystem;
    }

    public String getSubsites() {
        return subsites;
    }

    public void setSubsites(String subsites) {
        this.subsites = subsites;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
    }
}
