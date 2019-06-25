package com.rcd.iotsubsys.service.event;

import com.rcd.iotsubsys.domain.event.AttributeRelationEquipment;
import com.rcd.iotsubsys.repository.event.AttributeRelationEquipmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AttributeRelationEquipmentService {
    private final AttributeRelationEquipmentRepository attributeRelationEquipmentRepository;

    public AttributeRelationEquipmentService(AttributeRelationEquipmentRepository attributeRelationEquipmentRepository) {
        this.attributeRelationEquipmentRepository = attributeRelationEquipmentRepository;
    }

    public void insertAllRelation(List<Map<String ,Object>> list){

        List<AttributeRelationEquipment> RelationList = new ArrayList<>();
        for (Map<String ,Object> map : list) {
            AttributeRelationEquipment attributeRelationEquipment = new AttributeRelationEquipment();
            attributeRelationEquipment.setAttribute((String) map.get("o"));
            attributeRelationEquipment.setEquipment((String) map.get("p"));
            attributeRelationEquipment.setGraphName((String) map.get("src"));
            RelationList.add(attributeRelationEquipment);
        }

        Iterable<AttributeRelationEquipment> it = RelationList;

        attributeRelationEquipmentRepository.saveAll(it);

    }

}
