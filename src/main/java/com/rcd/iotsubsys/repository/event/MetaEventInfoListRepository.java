package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.MetaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MetaEventInfoListRepository extends JpaRepository<MetaEvent, Long> {

    //获取知识
    @Query(value = "select DISTINCT graph_id label,graph_id value from knowledge_knowledge_info ", nativeQuery = true)
    List<Map<String, Object>> findIot();

    //获取系统
    @Query(value = "select DISTINCT iot_system label,iot_system value from attribute_relation_equipment where graph_name = ?1", nativeQuery = true)
    List<Map<String, Object>> findSystem(String graph_name);

    //获取子站
    @Query(value = "select DISTINCT subsites label,subsites value from attribute_relation_equipment where iot_system = ?1", nativeQuery = true)
    List<Map<String, Object>> findSubsites(String system);

    //获取子系统
    @Query(value = "select DISTINCT subsystem label,subsystem value from attribute_relation_equipment where subsites = ?1", nativeQuery = true)
    List<Map<String, Object>> findSubsystem(String subsites);

    //获取设备
    @Query(value = "select DISTINCT equipment label,equipment value from attribute_relation_equipment where subsystem = ?1", nativeQuery = true)
    List<Map<String, Object>> findEquipment(String subsystem);

    //获取属性
    @Query(value = "select DISTINCT attribute label,attribute value from attribute_relation_equipment where equipment = ?1", nativeQuery = true)
    List<Map<String, Object>> findAttribute(String equipment);
}
