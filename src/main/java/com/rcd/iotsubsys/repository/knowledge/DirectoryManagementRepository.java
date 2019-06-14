package com.rcd.iotsubsys.repository.knowledge;

import com.rcd.iotsubsys.domain.knowledge.DirectoryManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DirectoryManagementRepository extends JpaRepository<DirectoryManagement, Long> {

    List<DirectoryManagement> findAllByType(String type);

    List<DirectoryManagement> findAll();

    //根据type获取name
    @Query("select name as label,id as value  from DirectoryManagement where type = ?1 ")
    List<Map<String,Object>> findNameByType(String type);

    @Query(value = "select name as label,id as value,parent_id  from knowledge_directory_management where type = '02' ",nativeQuery = true)
    List<Map<String,Object>> findDepartment();

    @Query(value = "select name as label,id as value,parent_id  from knowledge_directory_management where type = '02' ",nativeQuery = true)
    List<Map<String,Object>> findParent();

}
