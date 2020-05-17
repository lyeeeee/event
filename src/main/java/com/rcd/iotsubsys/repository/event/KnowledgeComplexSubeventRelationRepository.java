package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexSubeventRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-11 20:15
 */
public interface KnowledgeComplexSubeventRelationRepository extends JpaRepository<KnowledgeComplexSubeventRelation, Long> {
    void deleteAllByComplexEventId(Long complexEventId);
    List<KnowledgeComplexSubeventRelation> getAllByComplexEventId(Long complexEventId);
    void deleteAllByType(Integer type);

    KnowledgeComplexSubeventRelation findByRelationIdName(String relationIdName);
}
