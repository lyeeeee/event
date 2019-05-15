package com.rcd.iotsubsys.service.knowledge;

import com.rcd.iotsubsys.domain.knowledge.KnowledgeInfo;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeInfoListRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public List<KnowledgeInfo> getKnowledgeListFindAll(){
        return knowledgeInfoListRepository.findAll();
    }
    public List<KnowledgeInfo> getKnowledgeListBySelect(String name,String field_name,String department_name,String meta_catalogue_name){

        KnowledgeInfo knowledgeInfo = new KnowledgeInfo();
        knowledgeInfo.setName("undefined".equals(name)?null:name);
        knowledgeInfo.setFieldName("undefined".equals(field_name)?null:field_name);
        knowledgeInfo.setDepartmentName("undefined".equals(department_name)?null:department_name);
        knowledgeInfo.setMetaCatalogueName("undefined".equals(meta_catalogue_name)?null:meta_catalogue_name);
        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<KnowledgeInfo> ex = Example.of(knowledgeInfo,matcher);

        return knowledgeInfoListRepository.findAll(ex);
    }

}
