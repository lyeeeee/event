package com.rcd.iotsubsys.domain.warn;

import javax.persistence.*;

/**
 * 原子事件表
 */
@Entity
@Table(name = "warn")
public class Warn {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 报警状态
     */
    @Column(name = "type", nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
