package com.rcd.iotsubsys.service.knowledge;

import com.rcd.iotsubsys.domain.knowledge.DirectoryManagement;
import com.rcd.iotsubsys.repository.knowledge.DirectoryManagementRepository;
import com.rcd.iotsubsys.service.knowledge.dto.DirectoryManagementDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class DirectoryManagementService {
    private final DirectoryManagementRepository directoryManagementRepository;

    public DirectoryManagementService(DirectoryManagementRepository directoryManagementRepository) {
        this.directoryManagementRepository = directoryManagementRepository;
    }


    public List<DirectoryManagement> getDirectoryByCode(String code) {
        return directoryManagementRepository.findAllByType(code);
    }

    public List<DirectoryManagement> getDirectoryBySelect(String name, String type) {

        final DirectoryManagement directoryManagement = new DirectoryManagement();
        directoryManagement.setName("undefined".equals(name) ? null : name);
        directoryManagement.setType("undefined".equals(type) ? null : type);

        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<DirectoryManagement> ex = Example.of(directoryManagement, matcher);

        return directoryManagementRepository.findAll(ex);
    }

    //增改
    public DirectoryManagement save(DirectoryManagementDTO directoryManagementDTO) {
        Date ss = new Date();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format0.format(ss.getTime());

        DirectoryManagement directoryManagement = new DirectoryManagement();

        directoryManagement.setId(directoryManagementDTO.getId());
        directoryManagement.setName(directoryManagementDTO.getName());
        directoryManagement.setType(directoryManagementDTO.getType());
        directoryManagement.setInsertTime(time);
        if (directoryManagementDTO.getParentId() != null && !"".equals(directoryManagementDTO.getParentId())) {
            DirectoryManagement parent = new DirectoryManagement();
            parent.setId(directoryManagementDTO.getParentId());
            directoryManagement.setParent(parent);
        }
        return directoryManagementRepository.save(directoryManagement);
    }

    public void delete(Long id) {
        directoryManagementRepository.findById(id).ifPresent(directoryManagement -> {
            directoryManagementRepository.delete(directoryManagement);
        });
    }

    public List<Map<String, Object>> findNameByType(String type) {
        return directoryManagementRepository.findNameByType(type);
    }

    public List<Map<String, Object>> getSelect(){
        List<Map<String, Object>> fieldList = directoryManagementRepository.findNameByType("01");
        List<Map<String, Object>> departmentList = directoryManagementRepository.findDepartment();

        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Map<String, Object> fieldMap:fieldList) {
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> resultMap = new HashMap<>();

            for (Map<String, Object> departmentMap:departmentList) {
                Map<String, Object> map = new HashMap<>();

                if (fieldMap.get("value").toString().equals(departmentMap.get("parent_id").toString())){
                    map.put("value",departmentMap.get("value"));
                    map.put("label",departmentMap.get("label"));
                    map.put("isLeaf",true);
                    list.add(map);
                }
            }
            if(list == null || list.size() < 1){//领域下无部门则不添加进级联选择
                continue;
            }
            resultMap.put("value",fieldMap.get("value"));
            resultMap.put("label",fieldMap.get("label"));
            resultMap.put("children",list);
            resultList.add(resultMap);
        }
        return resultList;
    }


}
