package com.rcd.iotsubsys.repository.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeInfoListRepository extends JpaRepository<KnowledgeInfo, Long> {

    KnowledgeInfo findByName(String name);
    List<KnowledgeInfo> findAll();
}
