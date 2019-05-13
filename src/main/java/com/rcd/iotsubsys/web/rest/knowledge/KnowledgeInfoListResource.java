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
    public ResponseEntity<KnowledgeInfo> getKnowledgeListByName(@RequestParam String name) {
//        log.debug("REST request to get all KnowledgeInfoListResource");

        if (name == null || "".equals(name)){return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }

        KnowledgeInfo knowledgeInfo = knowledgeInfoListService.getKnowledgeListByName(name);
        if(knowledgeInfo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(knowledgeInfo);
    }


}
