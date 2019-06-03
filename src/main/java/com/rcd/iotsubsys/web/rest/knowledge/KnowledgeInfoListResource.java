package com.rcd.iotsubsys.web.rest.knowledge;

import com.codahale.metrics.annotation.Timed;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeInfo;
import com.rcd.iotsubsys.service.knowledge.KnowledgeInfoListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 知识列表
 */
@RestController
@RequestMapping("/api/knowledge/infoList")
public class KnowledgeInfoListResource {

    private final Logger log = LoggerFactory.getLogger(KnowledgeInfoListResource.class);

    private final KnowledgeInfoListService knowledgeInfoListService;

    public KnowledgeInfoListResource(KnowledgeInfoListService knowledgeInfoListService) {
        this.knowledgeInfoListService = knowledgeInfoListService;
    }

    @GetMapping()
    @Timed
    public ResponseEntity<List<KnowledgeInfo>> getKnowledgeListByName(@RequestParam(required = false) String name, @RequestParam(required = false) String field_name, @RequestParam(required = false) String department_name, @RequestParam(required = false) String meta_catalogue_name) {
//        log.debug("REST request to get all KnowledgeInfoListResource");

//        if (name == null || "".equals(name)){return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

//        KnowledgeInfo knowledgeInfo = knowledgeInfoListService.getKnowledgeListByName(name);
//        List<KnowledgeInfo> knowledgeInfo = knowledgeInfoListService.getKnowledgeListFindAll();
//        List<KnowledgeInfo> knowledgeInfo = knowledgeInfoListService.getKnowledgeListBySelect(name,field_name,department_name,meta_catalogue_name);
        List<KnowledgeInfo> knowledgeInfo = knowledgeInfoListService.getKnowledgeListBySelect(name, field_name, department_name, meta_catalogue_name);

        if (knowledgeInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(knowledgeInfo);
    }

//    @PostMapping("/by")
//    @Timed
//    public ResponseEntity<List<KnowledgeInfo>> getKnowledgeList(@RequestBody String name,@RequestBody String field_name,@RequestBody String department_name,@RequestBody String meta_catalogue_name) {

//        List<KnowledgeInfo> knowledgeInfo = knowledgeInfoListService.getKnowledgeListBySelect(name,field_name,department_name,meta_catalogue_name);
//
//        if(knowledgeInfo == null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(knowledgeInfo);
//    }


}
