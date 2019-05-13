package com.rcd.iotsubsys.service.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeInfo;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeInfoListRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class KnowledgeInfoListService {

    private final KnowledgeInfoListRepository knowledgeInfoListRepository;

    public KnowledgeInfoListService(KnowledgeInfoListRepository knowledgeInfoListRepository) {
        this.knowledgeInfoListRepository = knowledgeInfoListRepository;
    }

    public KnowledgeInfo getKnowledgeListByName(String name){
        return knowledgeInfoListRepository.findByName(name);
    }

}
