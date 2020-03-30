package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-21 17:54
 */
@Entity
@Table(name = "knowledge_file")
@Proxy(lazy = false)
public class KnowledgeFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 知识模型名称
     */
    @Column(name = "model_name", nullable = false)
    private String modelName;

    /***
     * 知识文件名称
     */
    @Column(name = "file_name", nullable = false)
    private String fileName;

    /***
     * 知识文件路径
     */
    @Column(name = "file_path", nullable = false)
    private String filePath;

    /***
     * 知识图url
     */
    @Column(name = "graph_id", nullable = false)
    private String graphId;

    /***
     * tdb知识模型名称
     */
    @Column(name = "tdb_model_name", nullable = false)
    private String tdbModelName;

    /***
     * 知识模型描述
     */
    @Column(name = "knowledge_synopsis", nullable = false)
    private String knowledgeSynopsis;

    /***
     * TDB数据库路径
     */
    @Column(name = "db_path", nullable = false)
    private String dbPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getGraphId() {
        return graphId;
    }

    public void setGraphId(String graphId) {
        this.graphId = graphId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getKnowledgeSynopsis() {
        return knowledgeSynopsis;
    }

    public void setKnowledgeSynopsis(String knowledgeSynopsis) {
        this.knowledgeSynopsis = knowledgeSynopsis;
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath;
    }

    public String getTdbModelName() {
        return tdbModelName;
    }

    public void setTdbModelName(String tdbModelName) {
        this.tdbModelName = tdbModelName;
    }
}
