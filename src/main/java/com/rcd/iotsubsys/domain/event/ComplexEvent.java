package com.rcd.iotsubsys.domain.event;

import javax.persistence.*;

/**
 * 复杂事件表
 *
 * 可以与原子事件表合成一张表，加一个type字段区分即可，此处因之前内容代码已完成，修改时间不够
 * 暂时先复制一份这样用
 */
@Entity
@Table(name = "complex_event")
public class ComplexEvent {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 复杂事件名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 简介
     */
    @Column(name = "synopsis", nullable = false)
    private String synopsis;

    /**
     * 插入时间
     */
    @Column(name = "insert_time")
    private String insertTime;


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

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }
}
