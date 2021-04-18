package com.rcd.iotsubsys.service.deduce;


import com.rcd.iotsubsys.domain.event.PubSubEvent;

import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2021-01-27 21:49
 */
public class CamputeRequest {

    private String requestId;

    /**
     * 计算基于给定事件集合，表达式是否可以满足0
     * 立即停止1
     * 增量任务2
     * */
    private int commondType;

    private List<String> formulas;

    private Map<String, Deque<PubSubEvent>> events;

    private boolean calcForSelf;

    private int calcForIndex = -1;

    public int getCalcForIndex() {
        return calcForIndex;
    }

    public void setCalcForIndex(int calcForIndex) {
        this.calcForIndex = calcForIndex;
    }

    public boolean isCalcForSelf() {
        return calcForSelf;
    }

    public void setCalcForSelf(boolean calcForSelf) {
        this.calcForSelf = calcForSelf;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getCommondType() {
        return commondType;
    }

    public void setCommondType(int commondType) {
        this.commondType = commondType;
    }

    public List<String> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<String> formulas) {
        this.formulas = formulas;
    }

    public Map<String, Deque<PubSubEvent>> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Deque<PubSubEvent>> events) {
        this.events = events;
    }
}
