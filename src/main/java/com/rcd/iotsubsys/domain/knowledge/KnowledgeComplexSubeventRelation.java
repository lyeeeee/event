package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-11 20:09
 */
@Entity
@Table(name = "knowledge_complex_subevent_relation")
@Proxy(lazy = false)
public class KnowledgeComplexSubeventRelation {

    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 创建时间
     */
    @Column(name = "insert_time", nullable = false)
    private String insertTime;

    /***
     * 关系名称
     */
    @Column(name = "relation_name", nullable = false)
    private String relationName;

    /***
     * 复杂事件id
     */
    @Column(name = "complex_event_id", nullable = false)
    private Long complexEventId;

    /***
     * 关系id名
     */
    @Column(name = "relation_id_name", nullable = false)
    private String relationIdName;

    /***
     * type
     */
    @Column(name = "type", nullable = false)
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public Long getComplexEventId() {
        return complexEventId;
    }

    public void setComplexEventId(Long complexEventId) {
        this.complexEventId = complexEventId;
    }

    public String getRelationIdName() {
        return relationIdName;
    }

    public void setRelationIdName(String relationIdName) {
        this.relationIdName = relationIdName;
    }
}
