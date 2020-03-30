package com.rcd.iotsubsys.repository.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-21 18:02
 */
public interface KnowledgeFileRepository extends JpaRepository<KnowledgeFile, Long> {
}
