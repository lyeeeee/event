package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexSubEvnet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-06 11:48
 */
public interface KnowledgeComplexSubEventRepository extends JpaRepository<KnowledgeComplexSubEvnet, Long> {

    List<KnowledgeComplexSubEvnet> getAllByComplexEventId(Long id);

    void deleteAllByComplexEventId(Long id);
}
