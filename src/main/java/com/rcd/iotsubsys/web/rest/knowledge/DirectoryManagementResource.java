package com.rcd.iotsubsys.web.rest.knowledge;

import com.codahale.metrics.annotation.Timed;
import com.rcd.iotsubsys.domain.knowledge.DirectoryManagement;
import com.rcd.iotsubsys.service.knowledge.DirectoryManagementService;
import com.rcd.iotsubsys.service.knowledge.dto.DirectoryManagementDTO;
import com.rcd.iotsubsys.web.rest.errors.BadRequestAlertException;
import com.rcd.iotsubsys.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * 目录管理
 */
@RestController
@RequestMapping("/api/knowledge/directory")
public class DirectoryManagementResource {
    private final Logger log = LoggerFactory.getLogger(DirectoryManagementResource.class);

    private final DirectoryManagementService directoryManagementService;

    public DirectoryManagementResource(DirectoryManagementService directoryManagementService) {
        this.directoryManagementService = directoryManagementService;
    }

    @GetMapping()
    @Timed
    public ResponseEntity<List<DirectoryManagement>> getDirectoryByCode(@RequestParam(required = false) String type, @RequestParam(required = false) String name) {
//        log.debug("REST request to get all KnowledgeInfoListResource");

//        List<DirectoryManagement> directoryInfo = directoryManagementService.getDirectoryByCode(code);
        List<DirectoryManagement> directoryInfo = directoryManagementService.getDirectoryBySelect(name, type);

        if (directoryInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(directoryInfo);
    }

//    @PostMapping("/building-subindicators")
//    @Timed
//    public ResponseEntity<DirectoryManagement> createBuildingSubindicator(@RequestBody DirectoryManagement directoryManagement) throws URISyntaxException {
//        log.debug("REST request to save BuildingSubindicator : {}", directoryManagement);
//        if (buildingSubindicator.getId() != null) {
//            throw new BadRequestAlertException("A new buildingSubindicator cannot already have an ID", ENTITY_NAME, "idexists");
//        }
//        BuildingSubindicator result = buildingSubindicatorRepository.save(buildingSubindicator);
//        return ResponseEntity.created(new URI("/api/building-subindicators/" + result.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
//            .body(result);
//    }
    @PostMapping("/insertField")
    @Timed
    public ResponseEntity<DirectoryManagement> saveDirectory(@RequestBody DirectoryManagementDTO directoryManagementDTO) throws URISyntaxException{

        DirectoryManagement result = directoryManagementService.save(directoryManagementDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    //删除
    @DeleteMapping("/deleteField/{id}")
    @Timed
    public ResponseEntity<Void> deleteDirectory(@PathVariable Long id){
//        logger.debug("REST request to delete student: {}", id);
        directoryManagementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert( "directory.deleted",id.toString())).build();
    }
    //根据type获取name
    @GetMapping("/getNameByType")
    @Timed
    public ResponseEntity<List<Map<String,Object>>> getNameByType(@RequestParam(required = false) String type) {
//        log.debug("REST request to get all KnowledgeInfoListResource");

        List<Map<String,Object>> directoryInfo = directoryManagementService.findNameByType(type);

        if (directoryInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(directoryInfo);
    }
    //获取级联选择数据
    @GetMapping("/getSelect")
    @Timed
    public ResponseEntity<List<Map<String,Object>>> getSelect() {
//        log.debug("REST request to get all KnowledgeInfoListResource");

        List<Map<String,Object>> directoryInfo = directoryManagementService.getSelect();

        if (directoryInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(directoryInfo);
    }
}
