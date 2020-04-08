package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-04 18:18
 */
@Entity
@Table(name = "knowledge_complex_event")
@Proxy(lazy = false)
public class KnowledgeComplexEvent {

    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 复杂事件名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /***
     * 复杂事件描述
     */
    @Column(name = "synopsis", nullable = false)
    private String synopsis;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    /***
     * 原子事关系
     */
    @Column(name = "relation", nullable = false)
    private String relation;

    /***
     * 目标关系
     */
    @Column(name = "target_relation", nullable = false)
    private String targetRelation;

    public String getTargetRelation() {
        return targetRelation;
    }

    public void setTargetRelation(String targetRelation) {
        this.targetRelation = targetRelation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
