package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-04 18:21
 */
public interface ComplexEventRepository extends JpaRepository<KnowledgeComplexEvent, Long> {

    List<KnowledgeComplexEvent> findAllByName(String name);

}
