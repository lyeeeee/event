package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.FolumaKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-11-22 15:32
 */
public interface KnowledgeFolumaRepository extends JpaRepository<FolumaKnowledge, Long> {

    List<FolumaKnowledge> getAllByIsComplete(Integer type);
}
