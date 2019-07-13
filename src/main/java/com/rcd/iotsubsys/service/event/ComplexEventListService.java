package com.rcd.iotsubsys.service.event;

import com.rcd.iotsubsys.domain.event.AttributeRelationComplexEvent;
import com.rcd.iotsubsys.domain.event.ComplexEvent;
import com.rcd.iotsubsys.repository.event.AttributeRelationComplexEventRepository;
import com.rcd.iotsubsys.repository.event.ComplexEventListRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ComplexEventListService {

    private final ComplexEventListRepository complexEventListRepository;
    private final AttributeRelationComplexEventRepository attributeRelationComplexEventRepository;

    public ComplexEventListService(ComplexEventListRepository complexEventListRepository, AttributeRelationComplexEventRepository attributeRelationComplexEventRepository) {
        this.complexEventListRepository = complexEventListRepository;
        this.attributeRelationComplexEventRepository = attributeRelationComplexEventRepository;
    }

    //查询复杂事件
    public List<ComplexEvent> getComplexEventListBySelect(String name) {

        ComplexEvent complexEvent = new ComplexEvent();
        complexEvent.setName("undefined".equals(name) ? null : name);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<ComplexEvent> ex = Example.of(complexEvent, matcher);

        return complexEventListRepository.findAll(ex);
    }

    //增改复杂事件
    public ComplexEvent save(ComplexEvent metaEvent) {
        Date ss = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());
        if("subsites".equals(metaEvent.getMetaEventCompany())){
            metaEvent.setMetaEventCompany("子站");
        }else if("subsystem".equals(metaEvent.getMetaEventCompany())){
            metaEvent.setMetaEventCompany("子系统");
        }else if("equipment".equals(metaEvent.getMetaEventCompany())){
            metaEvent.setMetaEventCompany("设备");
        }else if("attribute".equals(metaEvent.getMetaEventCompany())){
            metaEvent.setMetaEventCompany("属性");
        }

        metaEvent.setInsertTime(time);
        return complexEventListRepository.save(metaEvent);
    }

    //删除复杂事件
    public void deleteComplexEvent(Long id) {
        complexEventListRepository.findById(id).ifPresent(metaEvent -> {
            complexEventListRepository.delete(metaEvent);
        });
    }

    //获取所有原子事件
    public List<Map<String, Object>> getAllMetaList() {
        return complexEventListRepository.getAllMetaList();
    }

    //获取所选原子事件列表
    public List<AttributeRelationComplexEvent> getMetaList(String complexId) {
        String type = "0";
        List<AttributeRelationComplexEvent> list = attributeRelationComplexEventRepository.findAllByComplexEventIdAndType(complexId, type);
//        List<Map<String, Object>> resultList = new ArrayList<>();
//        for (AttributeRelationComplexEvent a : list) {
//            Map<String, Object> resultMap = new HashMap<>();
//            Map<String, Object> map = complexEventListRepository.getMetaInfo(a.getMetaEventId());
//            if (map.isEmpty()) {
//                continue;
//            }
//            resultMap.put("id", a.getId());//涉及到删除问题，暂时先这样写。
//            resultMap.put("name", map.get("name"));
//            resultMap.put("synopsis", map.get("synopsis"));
//            resultList.add(resultMap);
//
//        }
//        if (resultList == null || resultList.size() < 1) {
//            return new ArrayList<>();
//        }
        return list;
    }

    //新增所选原子事件
    public AttributeRelationComplexEvent addMeta(AttributeRelationComplexEvent attributeRelationComplexEvent) {

        return attributeRelationComplexEventRepository.save(attributeRelationComplexEvent);
    }

    //删除所选原子事件
    public void deleteMeta(Long id) {
        attributeRelationComplexEventRepository.findById(id).ifPresent(attributeRelationMetaEvent -> {
            attributeRelationComplexEventRepository.delete(attributeRelationMetaEvent);
        });
    }

    //获取属性关系列表
    public List<AttributeRelationComplexEvent> getAttributeList(String relationId) {
        String type = "1";
        return attributeRelationComplexEventRepository.findAllByComplexEventIdAndType(relationId, type);
    }

    //获取原子事件范围
    public List<Map<String, Object>> getMetaEventRange(String type) {
        if("subsites".equals(type)){
            return  complexEventListRepository.findSubsites();
        }else if ("subsystem".equals(type)){
            return  complexEventListRepository.findSubsystem();
        }else if ("equipment".equals(type)){
            return  complexEventListRepository.findEquipment();
        }else if ("attribute".equals(type)){
            return  complexEventListRepository.findAttribute();
        }
        return new ArrayList<>();
    }

}
