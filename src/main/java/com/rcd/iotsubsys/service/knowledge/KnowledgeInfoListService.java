package com.rcd.iotsubsys.service.knowledge;

import com.rcd.iotsubsys.domain.knowledge.DirectoryManagement;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeInfo;
import com.rcd.iotsubsys.repository.knowledge.DirectoryManagementRepository;
import com.rcd.iotsubsys.repository.knowledge.KnowledgeInfoListRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class KnowledgeInfoListService {

    private final KnowledgeInfoListRepository knowledgeInfoListRepository;
    private final DirectoryManagementRepository directoryManagementRepository;

    public KnowledgeInfoListService(KnowledgeInfoListRepository knowledgeInfoListRepository,DirectoryManagementRepository directoryManagementRepository) {
        this.knowledgeInfoListRepository = knowledgeInfoListRepository;
        this.directoryManagementRepository = directoryManagementRepository;
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

    public Map<String,Object> insertToMysql(Map<String,Object> map){
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);
        //查询元目录的父信息
        Optional<DirectoryManagement> optional = directoryManagementRepository.findById(Long.parseLong(map.get("metaId").toString()));
        //插入
        KnowledgeInfo knowledgeInfo = new KnowledgeInfo();
        knowledgeInfo.setName((String)map.get("name"));
        knowledgeInfo.setDepartmentId(optional.get().getParent().getId());
        knowledgeInfo.setDepartmentName(optional.get().getParent().getName());
        knowledgeInfo.setFieldId(optional.get().getParent().getParent().getId());
        knowledgeInfo.setFieldName(optional.get().getParent().getParent().getName());
        knowledgeInfo.setMetaCatalogueId(optional.get().getId());
        knowledgeInfo.setMetaCatalogueName(optional.get().getName());
        knowledgeInfo.setKnowledgeSynopsis((String)map.get("knowledgeSynopsis"));
        knowledgeInfo.setGraphId((String)map.get("graphName"));

        knowledgeInfoListRepository.save(knowledgeInfo);

        result.put("success",true);
        return result;
    }


}
