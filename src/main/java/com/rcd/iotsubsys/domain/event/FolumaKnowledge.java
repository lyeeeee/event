package com.rcd.iotsubsys.domain.event;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-11-22 15:24
 */

@Entity
@Table(name = "knowledge_foluma")
public class FolumaKnowledge {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 公式名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 是否是全局的
     */
    @Column(name = "is_global", nullable = false)
    private Integer isGlobal;

    /**
     * 是否是完整的
     */
    @Column(name = "is_complete")
    private Integer isComplete;

    /**
     * 公式内容
     */
    @Column(name = "content")
    private String content;
    /**
     * 公式变量对应具体知识
     */
    @Column(name = "var_knowledge_r")
    private String relation;

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

    public Integer getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(Integer isGlobal) {
        this.isGlobal = isGlobal;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
