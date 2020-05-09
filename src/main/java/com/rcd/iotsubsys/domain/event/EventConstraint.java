package com.rcd.iotsubsys.domain.event;

import java.util.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-05-01 16:24
 */
public class EventConstraint {

    /**
     * 属性限制id 对应限制
     * */
    private Map<Long, Valid> valids = new HashMap<>();

    /**
     * 原子事件id+属性名对应限制
     * */
    public Map<String, List<Valid>> validsMap = new HashMap<>();

    private String logicRelation;

    private Set<String> deviceNameSet = new HashSet<>();

    private Set<String> dataNameSet = new HashSet<>();

    private Set<String> nameSet = new HashSet<>();

    private Map<String, MetaEventAttrRelation> attrRelationMap = new HashMap<>();

    public EventConstraint() {

    }

    public void addMetaEventAttrRelation(MetaEventAttrRelation metaEventAttrRelation) {
        this.attrRelationMap.put(metaEventAttrRelation.getDeviceName() + metaEventAttrRelation.getDataName(), metaEventAttrRelation);
    }

    public void addDeviceNameAndDataName(String deviceName, String dataName) {
        this.nameSet.add(deviceName + dataName);
        this.deviceNameSet.add(deviceName);
        this.dataNameSet.add(dataName);
    }

    public void addValid(Valid valid) {
        valids.put(valid.getId(), valid);
        String s = valid.getMetaEventId() + valid.getAttrName();
        List<Valid> tmp = validsMap.getOrDefault(s, new ArrayList<>());
        tmp.add(valid);
        validsMap.put(s, tmp);
    }

    public Valid getValid(Long id) {
        return valids.get(valids.get(id));
    }
    public String getLogicRelation() {
        return logicRelation;
    }

    public void setLogicRelation(String logicRelation) {
        this.logicRelation = logicRelation;
    }

    /**
     * 判断消息是否在事件范围内
     * */
    public boolean inRange(String deviceName, String dataName) {
        if (this.nameSet.contains(deviceName + dataName)) {
            return true;
        }
        return false;
    }

    public boolean notValidValue(PubSubEvent event) {

        String deviceNameAndDataName = event.getDeviceNameAndDataName();
        MetaEventAttrRelation relation = this.attrRelationMap.get(deviceNameAndDataName);
        String idAndAttrName = relation.getMetaEventId() + relation.getTopicAttribute();
        List<Valid> valids = this.validsMap.get(idAndAttrName);
        for (Valid valid : valids) {
            if (!valid.valid(event.getValue())) {
                return true;
            }
        }
        return false;
    }
}
