package com.rcd.iotsubsys.web.rest.event;

import com.codahale.metrics.annotation.Timed;
import com.rcd.iotsubsys.domain.event.AttributeRelationMetaEvent;
import com.rcd.iotsubsys.domain.event.MetaEvent;
import com.rcd.iotsubsys.service.event.MetaEventInfoListService;
import com.rcd.iotsubsys.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 原子事件列表
 */
@RestController
@RequestMapping("/api/metaEvent/infoList")
public class MetaEventInfoListResource {

    private final Logger log = LoggerFactory.getLogger(MetaEventInfoListResource.class);
    private final MetaEventInfoListService metaEventInfoListService;

    public MetaEventInfoListResource(MetaEventInfoListService metaEventInfoListService) {
        this.metaEventInfoListService = metaEventInfoListService;
    }

    @GetMapping()
    @Timed
    public ResponseEntity<List<MetaEvent>> getMetaEventList(@RequestParam(required = false) String name) {
//        log.debug("REST request to get all KnowledgeInfoListResource");

//        if (name == null || "".equals(name)){return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        List<MetaEvent> metaEvent = metaEventInfoListService.getMetaEventListBySelect(name);

        if (metaEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(metaEvent);
    }

    //新增或修改
    @PostMapping("/insertMetaEvent")
    @Timed
    public ResponseEntity<MetaEvent> saveDirectory(@RequestBody MetaEvent metaEvent) {

        MetaEvent result = metaEventInfoListService.save(metaEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //删除
    @DeleteMapping("/deleteMetaEvent/{id}")
    @Timed
    public ResponseEntity<Void> deleteMetaEvent(@PathVariable Long id) {
//        logger.debug("REST request to delete student: {}", id);
        metaEventInfoListService.deleteMetaEvent(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("directory.deleted", id.toString())).build();
    }

    //获取对应关系列表
    @GetMapping("/getRelationList")
    @Timed
    public ResponseEntity<List<AttributeRelationMetaEvent>> getRelationList(@RequestParam(required = false) String relationId) {
//        log.debug("REST request to get all KnowledgeInfoListResource");

//        if (name == null || "".equals(name)){return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        List<AttributeRelationMetaEvent> attributeRelationMetaEvent = metaEventInfoListService.getRelationList(relationId);

        if (attributeRelationMetaEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(attributeRelationMetaEvent);
    }
    //获取级联选择数据
    @GetMapping("/getSelect")
    @Timed
    public ResponseEntity<List<Map<String,Object>>> getSelect() {
//        log.debug("REST request to get all KnowledgeInfoListResource");

        List<Map<String,Object>> directoryInfo = metaEventInfoListService.getSelect();

        if (directoryInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(directoryInfo);
    }
    //新增对应关系
    @PostMapping("/addRelation")
    @Timed
    public ResponseEntity<AttributeRelationMetaEvent> addRelation(@RequestBody AttributeRelationMetaEvent attributeRelationMetaEvent) {

        AttributeRelationMetaEvent result = metaEventInfoListService.addRelation(attributeRelationMetaEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    //删除对应关系
    @DeleteMapping("/deleteRelation/{id}")
    @Timed
    public ResponseEntity<Void> deleteRelation(@PathVariable Long id) {
//        logger.debug("REST request to delete student: {}", id);
        metaEventInfoListService.deleteRelation(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("directory.deleted", id.toString())).build();
    }


}
