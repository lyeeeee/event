package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-24 17:52
 */
@Entity
@Table(name = "knowledge_meta_event")
@Proxy(lazy = false)
public class KnowledgeMetaEvent {

    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 原子事件名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /***
     * 原子事件描述
     */
    @Column(name = "synopsis", nullable = false)
    private String synopsis;

    /***
     * topic
     */
    @Column(name = "topic", nullable = false)
    private String topic;

    /***
     * 知识id
     */
    @Column(name = "knowledge_id", nullable = false)
    private Long knowledgeId;

    /***
     * 知识名称
     */
    @Column(name = "knowledge", nullable = false)
    private String knowledge;

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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Long getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Long knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }
}
