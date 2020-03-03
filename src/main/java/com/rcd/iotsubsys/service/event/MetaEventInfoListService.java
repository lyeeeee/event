package com.rcd.iotsubsys.service.event;

import com.rcd.iotsubsys.domain.event.AttributeRelationMetaEvent;
import com.rcd.iotsubsys.domain.event.MetaEvent;
import com.rcd.iotsubsys.repository.event.AttributeRelationMetaEventRepository;
import com.rcd.iotsubsys.repository.event.MetaEventInfoListRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class MetaEventInfoListService {
    private final AttributeRelationMetaEventRepository attributeRelationMetaEventRepository;
    private final MetaEventInfoListRepository metaEventInfoListRepository;

    public MetaEventInfoListService(AttributeRelationMetaEventRepository attributeRelationMetaEventRepository,
                                    MetaEventInfoListRepository metaEventInfoListRepository) {
        this.attributeRelationMetaEventRepository = attributeRelationMetaEventRepository;
        this.metaEventInfoListRepository = metaEventInfoListRepository;
    }

    //查询原子事件
    public List<MetaEvent> getMetaEventListBySelect(String name) {

        MetaEvent metaEvent = new MetaEvent();
        metaEvent.setName("undefined".equals(name) ? null : name);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<MetaEvent> ex = Example.of(metaEvent, matcher);

        return metaEventInfoListRepository.findAll(ex);
    }

    //增改原子事件
    public MetaEvent save(MetaEvent metaEvent) {
        Date ss = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());

        metaEvent.setInsertTime(time);
        return metaEventInfoListRepository.save(metaEvent);
    }

    //删除原子事件
    public void deleteMetaEvent(Long id) {
        metaEventInfoListRepository.findById(id).ifPresent(metaEvent -> {
            metaEventInfoListRepository.delete(metaEvent);
        });
    }

    //查询对应关系
    public List<AttributeRelationMetaEvent> getRelationList(String relationId) {
        return attributeRelationMetaEventRepository.findAllByMetaEventId(relationId);
    }
    //查询知识
    public List<Map<String, Object>> getIotList() {
        return metaEventInfoListRepository.findIot();
    }
    //查询系统
    public List<Map<String, Object>> getSystemList(String graph_name) {
        return metaEventInfoListRepository.findSystem(graph_name);
    }
    //查询子站
    public List<Map<String, Object>> getSubsitesList(String iot_system) {
        return metaEventInfoListRepository.findSubsites(iot_system);
    }
    //查询子系统
    public List<Map<String, Object>> getSubsystemList(String subsites) {
        return metaEventInfoListRepository.findSubsystem(subsites);
    }
    //查询设备
    public List<Map<String, Object>> getEquipmentList(String subsystem) {
        return metaEventInfoListRepository.findEquipment(subsystem);
    }
    //查询属性
    public List<Map<String, Object>> getAttributeList(String equipment) {
        return metaEventInfoListRepository.findAttribute(equipment);
    }

    public List<Map<String, Object>> getSelect() {
        List<Map<String, Object>> equipmentList = attributeRelationMetaEventRepository.findEquipment();
        List<Map<String, Object>> attributeList = attributeRelationMetaEventRepository.findAttribute();

        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> equipMap : equipmentList) {
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> resultMap = new HashMap<>();

            for (Map<String, Object> attributeMap : attributeList) {
                Map<String, Object> map = new HashMap<>();

                if (equipMap.get("equipment").toString().equals(attributeMap.get("equipment").toString())) {
                    map.put("value", attributeMap.get("attribute"));
                    map.put("label", attributeMap.get("attribute"));
                    map.put("isLeaf", true);
                    list.add(map);
                }
            }
            resultMap.put("value", equipMap.get("equipment"));
            resultMap.put("label", equipMap.get("equipment"));
            resultMap.put("children", list);
            resultList.add(resultMap);
        }
        return resultList;
    }

    //新增对应关系
    public AttributeRelationMetaEvent addRelation(AttributeRelationMetaEvent attributeRelationMetaEvent) {

        return attributeRelationMetaEventRepository.save(attributeRelationMetaEvent);
    }

    //删除对应关系
    public void deleteRelation(Long id) {
        attributeRelationMetaEventRepository.findById(id).ifPresent(attributeRelationMetaEvent -> {
            attributeRelationMetaEventRepository.delete(attributeRelationMetaEvent);
        });
    }

}
