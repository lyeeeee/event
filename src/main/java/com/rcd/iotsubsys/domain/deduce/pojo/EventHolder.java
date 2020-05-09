package com.rcd.iotsubsys.domain.deduce.pojo;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-28 09:43
 */
public class EventHolder {
    public Event event;
    public long time;

    public EventHolder(Event event) {
        this.event = event;
        this.time = System.currentTimeMillis();
    }
}
