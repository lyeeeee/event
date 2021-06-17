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
@Table(name = "knowledge_complex_event_alarm")
@Proxy(lazy = false)
public class KnowledgeComplexEventAlarm {

    private static final long serialVersionUID = 1L;

    /***
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /***
     * 系统
     */
    @Column(name = "sys", nullable = false)
    private String sys;

    /***
     * 复杂事件
     */
    @Column(name = "complex_event", nullable = false)
    private String complexEvent;

    /***
     * 复杂事件说明
     */
    @Column(name = "complex_event_synopsis", nullable = false)
    private String complexEventSynopsis;

    /***
     * 站点
     */
    @Column(name = "site", nullable = false)
    private String site;

    /***
     * 时间
     */
    @Column(name = "time", nullable = false)
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getComplexEvent() {
        return complexEvent;
    }

    public void setComplexEvent(String complexEvent) {
        this.complexEvent = complexEvent;
    }

    public String getComplexEventSynopsis() {
        return complexEventSynopsis;
    }

    public void setComplexEventSynopsis(String complexEventSynopsis) {
        this.complexEventSynopsis = complexEventSynopsis;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
