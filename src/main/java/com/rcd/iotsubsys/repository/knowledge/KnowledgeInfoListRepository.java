package com.rcd.iotsubsys.repository.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeInfoListRepository extends JpaRepository<KnowledgeInfo, Long> {

    KnowledgeInfo findByName(String name);
}
