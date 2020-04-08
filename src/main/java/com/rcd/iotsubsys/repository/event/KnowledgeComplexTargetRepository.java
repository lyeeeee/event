package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-06 11:48
 */
public interface KnowledgeComplexTargetRepository extends JpaRepository<KnowledgeComplexTarget, Long> {

    List<KnowledgeComplexTarget> getAllByComplexEventId(Long id);
}
