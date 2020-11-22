package com.rcd.iotsubsys.domain.knowledge;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-10-27 16:37
 */
@Entity
@Table(name = "seledted_knowledge")
public class SelectedKnowledge {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 被选择的知识id
     */
    @Column(name = "knowledge_id", nullable = false)
    private Long knowledgeId;

    /**
     * 知识所属的复杂事件id
     */
    @Column(name = "complex_id", nullable = false)
    private Long complexId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Long knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public Long getComplexId() {
        return complexId;
    }

    public void setComplexId(Long complexId) {
        this.complexId = complexId;
    }
}
