package com.rcd.iotsubsys.web.rest.event;

import com.codahale.metrics.annotation.Timed;
import com.rcd.iotsubsys.domain.event.AttributeRelationComplexEvent;
import com.rcd.iotsubsys.domain.event.ComplexEvent;
import com.rcd.iotsubsys.service.event.ComplexEventListService;
import com.rcd.iotsubsys.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 复杂事件列表
 */
@RestController
@RequestMapping("/api/complexEvent/infoList")
public class ComplexEventListResource {
    private final Logger log = LoggerFactory.getLogger(ComplexEventListResource.class);
    private final ComplexEventListService complexEventListService;

    public ComplexEventListResource(ComplexEventListService complexEventListService) {
        this.complexEventListService = complexEventListService;
    }

    @GetMapping()
    @Timed
    public ResponseEntity<List<ComplexEvent>> getComplexEventList(@RequestParam(required = false) String name) {

        List<ComplexEvent> ComplexEvent = complexEventListService.getComplexEventListBySelect(name);

        if (ComplexEvent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(ComplexEvent);
    }

    //新增或修改
    @PostMapping("/insertComplexEvent")
    @Timed
    public ResponseEntity<ComplexEvent> saveDirectory(@RequestBody ComplexEvent ComplexEvent) {

        ComplexEvent result = complexEventListService.save(ComplexEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //删除
    @DeleteMapping("/deleteComplexEvent/{id}")
    @Timed
    public ResponseEntity<Void> deleteComplexEvent(@PathVariable Long id) {
//        logger.debug("REST request to delete student: {}", id);
        complexEventListService.deleteComplexEvent(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("directory.deleted", id.toString())).build();
    }

    //获取所有原子事件
    @GetMapping("/getAllMetaList")
    @Timed
    public ResponseEntity<List<Map<String, Object>>> getAllMetaList() {
        List<Map<String, Object>> iotList = complexEventListService.getAllMetaList();
        if (iotList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(iotList);
    }
    //获取所选原子事件列表
    @GetMapping("/getMetaList")
    @Timed
    public ResponseEntity<List<AttributeRelationComplexEvent>> getMetaList(@RequestParam(required = false) String complexId) {

        List<AttributeRelationComplexEvent> attributeRelationComplexEvents = complexEventListService.getMetaList(complexId);

//        if (attributeRelationComplexEvents == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return ResponseEntity.ok(attributeRelationComplexEvents);
    }
    //新增所选原子事件
    @PostMapping("/addMeta")
    @Timed
    public ResponseEntity<AttributeRelationComplexEvent> addMeta(@RequestBody AttributeRelationComplexEvent attributeRelationComplexEvent) {

        AttributeRelationComplexEvent result = complexEventListService.addMeta(attributeRelationComplexEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    //删除所选原子事件
    @DeleteMapping("/deleteMeta/{id}")
    @Timed
    public ResponseEntity<Void> deleteMeta(@PathVariable Long id) {
//        logger.debug("REST request to delete student: {}", id);
        complexEventListService.deleteMeta(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("directory.deleted", id.toString())).build();
    }

    //获取属性关系列表
    @GetMapping("/getAttributeList")
    @Timed
    public ResponseEntity<List<AttributeRelationComplexEvent>> getAttributeList(@RequestParam(required = false) String complexId) {
        List<AttributeRelationComplexEvent> attributeList = complexEventListService.getAttributeList(complexId);
        if (attributeList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(attributeList);
    }
    //新增属性关系
    @PostMapping("/addAttributeList")
    @Timed
    public ResponseEntity<AttributeRelationComplexEvent> addAttributeList(@RequestBody AttributeRelationComplexEvent attributeRelationComplexEvent) {

        AttributeRelationComplexEvent result = complexEventListService.addMeta(attributeRelationComplexEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    //删除属性关系
    @DeleteMapping("/deleteAttribute/{id}")
    @Timed
    public ResponseEntity<Void> deleteAttribute(@PathVariable Long id) {
//        logger.debug("REST request to delete student: {}", id);
        complexEventListService.deleteMeta(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("directory.deleted", id.toString())).build();
    }
    //获取原子事件范围List
    @GetMapping("/getMetaEventRange")
    @Timed
    public ResponseEntity<List<Map<String, Object>>> getMetaEventRange(@RequestParam(required = false) String type) {
        List<Map<String, Object>> list = complexEventListService.getMetaEventRange(type);
        if (list == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    //获取目标列表
    @GetMapping("/getTargetList")
    @Timed
    public ResponseEntity<List<AttributeRelationComplexEvent>> getTargetList(@RequestParam(required = false) String complexId) {
        List<AttributeRelationComplexEvent> attributeList = complexEventListService.getTargetList(complexId);
        if (attributeList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(attributeList);
    }
    //新增属性关系
    @PostMapping("/addTargetList")
    @Timed
    public ResponseEntity<AttributeRelationComplexEvent> addTargetList(@RequestBody AttributeRelationComplexEvent attributeRelationComplexEvent) {

        AttributeRelationComplexEvent result = complexEventListService.addMeta(attributeRelationComplexEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    //删除属性关系
    @DeleteMapping("/deleteTarget/{id}")
    @Timed
    public ResponseEntity<Void> deleteTarget(@PathVariable Long id) {
//        logger.debug("REST request to delete student: {}", id);
        complexEventListService.deleteMeta(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("directory.deleted", id.toString())).build();
    }

    
}
