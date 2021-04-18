package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeComplexEventAlarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-11-22 15:32
 */
public interface KnowledgeComplexEventAlarmRepository extends JpaRepository<KnowledgeComplexEventAlarm, Long> {

    List<KnowledgeComplexEventAlarm> getAllByComplexEvent(String complexEvent);
}
