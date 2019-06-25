package com.rcd.iotsubsys.domain.event;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

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
    @Column(name = "graphName", nullable = false)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
