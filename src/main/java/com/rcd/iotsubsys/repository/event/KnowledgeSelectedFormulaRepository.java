package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeSelectedFormula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2021-01-16 17:37
 */
public interface KnowledgeSelectedFormulaRepository extends JpaRepository<KnowledgeSelectedFormula, Long> {
    List<KnowledgeSelectedFormula> getAllByComplexId(Long complexId);
}
