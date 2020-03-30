package com.rcd.iotsubsys.repository.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-23 23:14
 */
public interface KnowledgeRepository extends JpaRepository<KnowledgeKnowledge, Long> {

    KnowledgeKnowledge getKnowledgeKnowledgeByDirNodeId(Long id);

}
