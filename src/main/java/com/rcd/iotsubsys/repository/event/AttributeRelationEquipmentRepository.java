package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.AttributeRelationEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AttributeRelationEquipmentRepository extends JpaRepository<AttributeRelationEquipment, Long> {

}
