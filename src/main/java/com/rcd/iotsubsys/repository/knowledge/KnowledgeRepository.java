package com.rcd.iotsubsys.repository.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-23 23:14
 */
public interface KnowledgeRepository extends JpaRepository<KnowledgeKnowledge, Long> {

    KnowledgeKnowledge getKnowledgeKnowledgeByDirNodeId(Long id);
    void deleteAllByModelName(String modelName);
    List<KnowledgeKnowledge> findAllByIdIn(List<Long> ids);
    List<KnowledgeKnowledge> getKnowledgeKnowledgeByModelName(String modelName);
}
