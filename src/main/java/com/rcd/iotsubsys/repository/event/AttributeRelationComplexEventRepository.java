package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.AttributeRelationComplexEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRelationComplexEventRepository extends JpaRepository<AttributeRelationComplexEvent, Long> {

    List<AttributeRelationComplexEvent> findAllByComplexEventIdAndType(String complexId,String type);


}
