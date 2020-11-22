package com.rcd.iotsubsys.repository.directory;

import com.rcd.iotsubsys.domain.directory.DirectoryNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-04 17:01
 */
public interface DirectoryRepository extends JpaRepository<DirectoryNode, Long> {


//    @Query("select *  from DirectoryManagement where parent_id = ?1 ")
//    List<DirectoryNode> getNodeByParentId(Long parentId);
     List<DirectoryNode> findAllByParentId(Long parentId);

     List<DirectoryNode> findAllByOwner(String owner);

     List<DirectoryNode> findAllByValue(String value);

     List<DirectoryNode> findAllByParentIdIn(Set<Long> ids);

     List<DirectoryNode> getAllByValue(String value);

    List<DirectoryNode> getAllByIdIn(List<Long> ids);
}
