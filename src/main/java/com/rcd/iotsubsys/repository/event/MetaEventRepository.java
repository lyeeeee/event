package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-25 10:52
 */
public interface MetaEventRepository extends JpaRepository<KnowledgeMetaEvent, Long> {

    KnowledgeMetaEvent findByName(String name);

    KnowledgeMetaEvent findAllByName(String name);
}
