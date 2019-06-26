package com.rcd.iotsubsys.domain.event;

import javax.persistence.*;

/**
 * owl文件中解析出的设备与属性的对应关系表
 */
@Entity
@Table(name = "attribute_relation_equipment")
public class AttributeRelationEquipment {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应graphDB数据库的图名
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
    @Column(name = "attribute", nullable = false)
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

    public String getGraphName() {
        return graphName;
    }

    public void setGraphName(String graphName) {
        this.graphName = graphName;
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
}
