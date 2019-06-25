package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.AttributeRelationMetaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AttributeRelationMetaEventRepository extends JpaRepository<AttributeRelationMetaEvent, Long> {
    List<AttributeRelationMetaEvent> findAllByMetaEventId(String metaEventId);

    @Query(value = "select DISTINCT equipment from attribute_relation_equipment ",nativeQuery = true)
    List<Map<String,Object>> findEquipment();
    @Query(value = "select DISTINCT equipment,attribute from attribute_relation_equipment ",nativeQuery = true)
    List<Map<String,Object>> findAttribute();
}
