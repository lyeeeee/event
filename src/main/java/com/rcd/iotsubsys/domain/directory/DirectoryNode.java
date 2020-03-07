package com.rcd.iotsubsys.domain.directory;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-04 16:53
 */
@Entity
@Table(name = "knowledge_directory_node")
public class DirectoryNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "n_name")
    private String nName;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "attributes")
    private String attr;

    @Column(name = "owner")
    private String owner;

    @Column(name = "value")
    private String value;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }
}
