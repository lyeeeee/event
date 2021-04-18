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
public class CamputeResponse{
    private boolean success;

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

    /**
     * 使得当前计算节点满足的事件集合，看一下能否让有共享变量的结点也在这些事件集上满足
     * */
    private Map<String, Deque<PubSubEvent>> events;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Deque<PubSubEvent>> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Deque<PubSubEvent>> events) {
        this.events = events;
    }
}
