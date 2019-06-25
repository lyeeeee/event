package com.rcd.iotsubsys.web.rest.knowledge;

import com.codahale.metrics.annotation.Timed;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeInfo;
import com.rcd.iotsubsys.service.event.AttributeRelationEquipmentService;
import com.rcd.iotsubsys.service.knowledge.KnowledgeInfoListService;
import com.rcd.iotsubsys.web.rest.util.GraphDBCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * 知识列表
 */
@RestController
@RequestMapping("/api/knowledge/infoList")
public class KnowledgeInfoListResource {

    private static String graphName = "";
    private final Logger log = LoggerFactory.getLogger(KnowledgeInfoListResource.class);
    private final KnowledgeInfoListService knowledgeInfoListService;
    private final AttributeRelationEquipmentService attributeRelationEquipmentService;

    public KnowledgeInfoListResource(KnowledgeInfoListService knowledgeInfoListService, AttributeRelationEquipmentService attributeRelationEquipmentService) {
        this.knowledgeInfoListService = knowledgeInfoListService;
        this.attributeRelationEquipmentService = attributeRelationEquipmentService;
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

    @PostMapping("/insertToMysql")
    @Timed
    public ResponseEntity<Map<String, Object>> insertToMysql(@RequestBody Map<String, Object> mapdata) throws URISyntaxException {

        graphName = (String) mapdata.get("graphName");
        Map<String, Object> result = knowledgeInfoListService.insertToMysql(mapdata);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/insertToGraphdb")
    @Timed
    public ResponseEntity<Map<String, Object>> insertToGraphdb(@RequestBody MultipartFile file) {

        try {
            File f = null;
            InputStream ins = file.getInputStream();
            f = new File(file.getOriginalFilename());
            inputStreamToFile(ins, f);

            GraphDBCommon.insertGraphDB(graphName, f);

            File del = new File(f.toURI());
            del.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Map<String,Object> result = knowledgeInfoListService.insertToMysql(mapdata);
        String sparql = "SELECT distinct ?p ?o ?src FROM NAMED <"+graphName+"> where { Graph ?src{ ?s ?p ?o }}";
        List<Map<String,Object>> result = GraphDBCommon.selectGraphDB1(sparql);
        attributeRelationEquipmentService.insertAllRelation(result);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/sparqlSelect")
    @Timed
    public ResponseEntity<List<Map<String,Object>>> sparqlSelect(@RequestParam(required = false) String sparql) {

        List<Map<String,Object>> result = GraphDBCommon.selectGraphDB(sparql);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }


    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
