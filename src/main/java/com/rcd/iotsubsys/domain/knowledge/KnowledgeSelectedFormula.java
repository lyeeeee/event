package com.rcd.iotsubsys.domain.knowledge;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2021-01-16 17:32
 */
@Entity
@Table(name = "knowledge_selected_formula")
@Proxy(lazy = false)
public class KnowledgeSelectedFormula {
    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 复杂事件id
     */
    @Column(name = "complex_id", nullable = false)
    private Long complexId;

    /***
     * 公式id
     */
    @Column(name = "formula_id", nullable = false)
    private Long formulaId;

    /***
     * 逻辑关系
     */
    @Column(name = "attribute_relation", nullable = false)
    private String attributeRelation;

    /***
     * 关系值
     */
    @Column(name = "relation_value", nullable = false)
    private String relationValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComplexId() {
        return complexId;
    }

    public void setComplexId(Long complexId) {
        this.complexId = complexId;
    }

    public Long getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Long formulaId) {
        this.formulaId = formulaId;
    }

    public String getAttributeRelation() {
        return attributeRelation;
    }

    public void setAttributeRelation(String attributeRelation) {
        this.attributeRelation = attributeRelation;
    }

    public String getRelationValue() {
        return relationValue;
    }

    public void setRelationValue(String relationValue) {
        this.relationValue = relationValue;
    }
}
