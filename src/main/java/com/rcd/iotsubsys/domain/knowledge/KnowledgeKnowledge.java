package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-22 18:10
 */
@Entity
@Table(name = "knowledge_knowledge")
@Proxy(lazy = false)
public class KnowledgeKnowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 知识名称
     */
    @Column(name = "knowledge_name", nullable = false)
    private String knowledgeName;

    /***
     * 知识uri
     */
    @Column(name = "knowledge_uri", nullable = false)
    private String knowledgeUri;

    /***
     * 知识文件id
     */
    @Column(name = "knowldege_file_id", nullable = false)
    private Long knowldegeFileId;

    /***
     * 知识目录id
     */
    @Column(name = "knowledge_dir", nullable = false)
    private Long knowledgeDir;

    /***
     * 知识记录类型
     */
    @Column(name = "type", nullable = false)
    private Integer type;

    /***
     * 知识描述
     */
    @Column(name = "knowledge_class_id", nullable = false)
    private Long knowledgeClassId;

    /***
     * 知识对应的目录结点id
     */
    @Column(name = "dir_node_id", nullable = false)
    private Long dirNodeId;

    /***
     * 所属模型名
     */
    @Column(name = "model_name", nullable = false)
    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowledgeUri() {
        return knowledgeUri;
    }

    public void setKnowledgeUri(String knowledgeUri) {
        this.knowledgeUri = knowledgeUri;
    }

    public Long getKnowldegeFileId() {
        return knowldegeFileId;
    }

    public void setKnowldegeFileId(Long knowldegeFileId) {
        this.knowldegeFileId = knowldegeFileId;
    }

    public Long getKnowledgeDir() {
        return knowledgeDir;
    }

    public void setKnowledgeDir(Long knowledgeDir) {
        this.knowledgeDir = knowledgeDir;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getKnowledgeClassId() {
        return knowledgeClassId;
    }

    public void setKnowledgeClassId(Long knowledgeClassId) {
        this.knowledgeClassId = knowledgeClassId;
    }

    public Long getDirNodeId() {
        return dirNodeId;
    }

    public void setDirNodeId(Long dirNodeId) {
        this.dirNodeId = dirNodeId;
    }
}
