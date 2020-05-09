package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.MetaEventAttrRelation;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-30 15:50
 */
public interface MetaEventAttrRelationRepository extends JpaRepository<MetaEventAttrRelation, Long> {

    List<MetaEventAttrRelation> findAllByMetaEventId(Long metaEventId);

    List<MetaEventAttrRelation> findAllByMetaEventIdIn(List<Long> metaEventIds);

    void deleteAllByMetaEventId(Long metaEventId);
}
