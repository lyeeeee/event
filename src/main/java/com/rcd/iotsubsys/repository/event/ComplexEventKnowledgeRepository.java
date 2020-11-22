package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.SelectedKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-10-27 16:36
 */
public interface ComplexEventKnowledgeRepository extends JpaRepository<SelectedKnowledge, Long>{

    List<SelectedKnowledge> findAllByComplexId(Long complexId);
    void removeAllByComplexId(Long complexId);
}
