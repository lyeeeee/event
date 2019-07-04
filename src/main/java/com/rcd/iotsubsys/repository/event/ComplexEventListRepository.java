package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.ComplexEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ComplexEventListRepository extends JpaRepository<ComplexEvent, Long> {

    //获取所有原子事件
    @Query(value = "select DISTINCT name label,id value from meta_event ", nativeQuery = true)
    List<Map<String, Object>> getAllMetaList();

    //获取复杂事件下原子事件的名称及简介
    @Query(value = "select DISTINCT id,name,synopsis from meta_event where id  = ?1", nativeQuery = true)
//    @Query(value = "select DISTINCT id,name,synopsis from meta_event where id in (?1)", nativeQuery = true)
    Map<String, Object> getMetaInfo(String MetaId);

    //获取原子事件范围 子站
    @Query(value = "select DISTINCT subsites label,subsites value from attribute_relation_equipment ", nativeQuery = true)
    List<Map<String, Object>> findSubsites();

    //获取原子事件范围 子系统
    @Query(value = "select DISTINCT subsystem label,subsystem value from attribute_relation_equipment ", nativeQuery = true)
    List<Map<String, Object>> findSubsystem();

    //获取原子事件范围 设备
    @Query(value = "select DISTINCT equipment label,equipment value from attribute_relation_equipment ", nativeQuery = true)
    List<Map<String, Object>> findEquipment();

    //获取原子事件范围 属性
    @Query(value = "select DISTINCT attribute label,attribute value from attribute_relation_equipment ", nativeQuery = true)
    List<Map<String, Object>> findAttribute();

}
