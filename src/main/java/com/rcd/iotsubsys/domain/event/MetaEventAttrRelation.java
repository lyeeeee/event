package com.rcd.iotsubsys.domain.event;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-30 15:46
 */
@Entity
@Table(name = "knowledge_attribute_relation_meta_event")
@Proxy(lazy = false)
public class MetaEventAttrRelation {

    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 知识属性名称
     */
    @Column(name = "knowledge_attribute", nullable = false)
    private String knowledgeAttribute;

    /***
     * 知识属性数据类型
     */
    @Column(name = "knowledge_attribute_type", nullable = false)
    private String knowledgeAttributeType;

    /***
     * 原子事件id
     */
    @Column(name = "meta_event_id", nullable = false)
    private Long metaEventId;

    /***
     * 主题属性
     */
    @Column(name = "topic_attribute", nullable = false)
    private String topicAttribute;

    /***
     * 主题属性数据类型
     */
    @Column(name = "topic_attribute_type", nullable = false)
    private String topicAttributeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKnowledgeAttribute() {
        return knowledgeAttribute;
    }

    public void setKnowledgeAttribute(String knowledgeAttribute) {
        this.knowledgeAttribute = knowledgeAttribute;
    }

    public String getKnowledgeAttributeType() {
        return knowledgeAttributeType;
    }

    public void setKnowledgeAttributeType(String knowledgeAttributeType) {
        this.knowledgeAttributeType = knowledgeAttributeType;
    }

    public Long getMetaEventId() {
        return metaEventId;
    }

    public void setMetaEventId(Long metaEventId) {
        this.metaEventId = metaEventId;
    }

    public String getTopicAttribute() {
        return topicAttribute;
    }

    public void setTopicAttribute(String topicAttribute) {
        this.topicAttribute = topicAttribute;
    }

    public String getTopicAttributeType() {
        return topicAttributeType;
    }

    public void setTopicAttributeType(String topicAttributeType) {
        this.topicAttributeType = topicAttributeType;
    }
}
