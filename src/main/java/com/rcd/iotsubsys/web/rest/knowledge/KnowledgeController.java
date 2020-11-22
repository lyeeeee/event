package com.rcd.iotsubsys.web.rest.knowledge;

import com.rcd.iotsubsys.dto.response.JsonResult;
import com.rcd.iotsubsys.dto.response.base.ResponseCode;
import com.rcd.iotsubsys.service.knowledge.KnowledgeService;
import com.rcd.iotsubsys.util.OwlClass;
import com.rcd.iotsubsys.util.OwlResourceData;
import com.rcd.iotsubsys.util.OwlResourceUtil;
import com.rcd.iotsubsys.util.equivalent_class;
import org.apache.jena.ontology.*;
import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.*;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-14 16:37
 */
@RestController
@RequestMapping("api/knowledge")
public class KnowledgeController {

    @Autowired
    KnowledgeService knowledgeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResult<Object> uploadKnowledgeFile(@RequestParam(name = "metaId") List<Long> metaId
        ,@RequestParam(name = "file")MultipartFile file
        ,@RequestParam(name = "name") String name
        ,@RequestParam(name = "graphName") String graphName
        ,@RequestParam(name = "knowledgeSynopsis")String knowledgeSynopsis) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("metaId", metaId);
        map.put("file", file);
        map.put("name", name);
        map.put("graphName", graphName);
        map.put("knowledgeSynopsis", knowledgeSynopsis);
        LOGGER.info("uploadKnowledgeFile...., param size:{}", map.size());
        if (StringUtils.isEmpty(name)) {
            return new JsonResult<>(ResponseCode.KNOWLEDGE_MODEL_NAME_EMPTY);
        }
        knowledgeService.checkUploadKnowledge(map);
        return new JsonResult<>();
    }

    @RequestMapping(value = "/getAllKnowledge", method = RequestMethod.GET)
    public JsonResult<Object> getAllKnowledge(@RequestParam(name = "knowledgeName" , required = false) String knowledgeName,
                                           @RequestParam(name = "field" , required = false) Long field,
                                           @RequestParam(name = "department" , required = false) Long department,
                                           @RequestParam(name = "metaDir" , required = false) Long metaDir) throws IOException {
        LOGGER.info("getAllKnowledge with knowledgeName:{},field:{},department:{},metaDir:{}", knowledgeName,field,department,metaDir);
        return knowledgeService.getKnowledge(knowledgeName, field, department, metaDir);
    }

    @RequestMapping(value = "/getKnowledgeProperty", method = RequestMethod.GET)
    public JsonResult<Object> add(@RequestParam(required = false) Long metaEventId) throws IOException {
        LOGGER.info("getKnowledgeProperty with knowledgeId:{}", metaEventId);
        return knowledgeService.getKnowledgeProperties(metaEventId);
    }

    @RequestMapping(value = "/getKnowledgePropertyByUri", method = RequestMethod.GET)
    public JsonResult<Object> getKnowledgePropertyByUri(@RequestParam(required = false) String knowledgeUri) throws IOException {
        LOGGER.info("getKnowledgeProperty with knowledgeUri:{}", knowledgeUri);
        return knowledgeService.getKnowledgeProperties(knowledgeUri);
    }

    @RequestMapping(value = "/getAllModel", method = RequestMethod.GET)
    public JsonResult<Object> getAllModel() throws IOException {
        LOGGER.info("getAllModel...");
        return knowledgeService.getAllModel();
    }

    @RequestMapping(value = "/deleteModel", method = RequestMethod.DELETE)
    public JsonResult<Object> deleteModel(Long fileId) throws IOException {
        LOGGER.info("deleteModel with param:{}", fileId);
        return knowledgeService.deleteModel(fileId);
    }

    @RequestMapping(value = "/getKnowledgeBySparql", method = RequestMethod.GET)
    public JsonResult<Object> getKnowledgeBySparql(String sql) throws IOException {
        LOGGER.info("getKnowledgeBySparql with param:{}", sql);
        return knowledgeService.getKnowledgeBySparql(sql);
    }

    @RequestMapping(value = "/getAllKnowledgeKnowledge", method = RequestMethod.GET)
    public JsonResult<Object> getAllKnowledgeKnowledge() throws IOException {
        return knowledgeService.getAllKnowledgeKnowledge();
    }

    public static void main(String[] args) throws FileNotFoundException {
        //创建dataset和model
//        Dataset dataset = TDBFactory.createDataset("dbData");
//        dataset.begin(ReadWrite.WRITE);
//        Model model = ModelFactory.createOntologyModel();
//        FileManager.get().readModel(model,"D:\\Users\\liyi\\Desktop\\1\\实验室\\光纤授时\\西安.owl" );
//        dataset.commit();

        //读取model
//        dataset.begin(ReadWrite.READ);
//        List<String> uriList = new ArrayList<>();
//        try {
//            Iterator<String> names = dataset.listNames();
//            String name;
//            while (names.hasNext()) {
//                name = names.next();
//                uriList.add(name);
//            }
//        } finally {
//            dataset.end();
//        }
//        uriList.forEach(a -> System.out.println(a));

        //读取model内容
//        dataset.begin(ReadWrite.READ);
//        Model test1 = dataset.getNamedModel("test1");
//
//        OntModelSpec spec;
//        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, test1);
//        ExtendedIterator<OntClass> ontClassExtendedIterator = model.listClasses();
//        while (ontClassExtendedIterator.hasNext()) {
//            System.out.println(ontClassExtendedIterator.next());
//        }
//        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
//        model.read(new FileInputStream("D:\\Users\\liyi\\Desktop\\1\\实验室\\光纤授时\\西安.owl"), null);
//        ExtendedIterator<OntClass> ontClassExtendedIterator1 = model.listClasses();
//        while(ontClassExtendedIterator1.hasNext()){
//            System.out.println(ontClassExtendedIterator1.next());
//        }
//        OntClass ontClass = model.getOntClass("http://www.w3.org/ns/sosa/FeatureOfInterest");
//        ExtendedIterator<OntClass> ontClassExtendedIterator = ontClass.listSubClasses();
//        Queue<OntClass> q = new LinkedList<>();
//        while (ontClassExtendedIterator.hasNext()) {
//            OntClass next = ontClassExtendedIterator.next();
//            if (next.hasSubClass()) {
//                ExtendedIterator<OntClass> ontClassExtendedIterator2 = next.listSubClasses();
//                while(ontClassExtendedIterator2.hasNext()) {
//                    q.offer(ontClassExtendedIterator2.next());
//                }
//            }
//            System.out.println(next);
//            System.out.println("uri:" + next.getURI());
//            //System.out.println("id:" + next.getId());
//            System.out.println("model:" + next.getModel());
//            System.out.println("namespace:" + next.getNameSpace());
//            System.out.println("localname:" + next.getLocalName());
//            ExtendedIterator<OntProperty> ontPropertyExtendedIterator = next.listDeclaredProperties();
//            while (ontPropertyExtendedIterator.hasNext()) {
//                System.out.println("            property:" + ontPropertyExtendedIterator.next());
//            }
//        }
//        System.out.println("===============================================================");
//        while (!q.isEmpty()) {
//            OntClass next = q.remove();
//            System.out.println(next);
//            System.out.println("uri:" + next.getURI());
//            //System.out.println("id:" + next.getId());
//            System.out.println("model:" + next.getModel());
//            System.out.println("namespace:" + next.getNameSpace());
//            System.out.println("localname:" + next.getLocalName());
//            ExtendedIterator<OntProperty> ontPropertyExtendedIterator = next.listDeclaredProperties();
//            while (ontPropertyExtendedIterator.hasNext()) {
//                System.out.println("            property:" + ontPropertyExtendedIterator.next());
//            }
//        }
       // dataset.addNamedModel("test2", model);
//        Iterator<String> stringIterator = dataset.listNames();
//        while (stringIterator.hasNext()) {
//            System.out.println(stringIterator.next());
//        }
//        Model namedModel = dataset.getNamedModel("1");
//        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, namedModel);
//        System.out.println(ontModel.getClass());
//        ExtendedIterator<OntClass> ontClassExtendedIterator = ontModel.listClasses();
//        while(ontClassExtendedIterator.hasNext()){
//            System.out.println(ontClassExtendedIterator.next());
//        }
//        dataset.close();
        Model model = ModelFactory.createDefaultModel();
        //Model model = dataset.getNamedModel("11");
        model.read(new FileInputStream("D:\\\\Users\\\\liyi\\\\Desktop\\\\1\\\\实验室\\\\光纤授时\\\\西安.owl"),null);
        //dataset.addNamedModel("test", model);
        //model = dataset.getNamedModel("test");
        OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM,model);
//        ExtendedIterator<OntClass> ontClassExtendedIterator = ontModel.listClasses();
//        while(ontClassExtendedIterator.hasNext()){
//            OntClass tmp = ontClassExtendedIterator.next();
//            System.out.println(tmp.getLocalName());
//            System.out.println(tmp.getURI());
//        }
        OntClass ontClass1 = ontModel.getOntClass("http://www.w3.org/ns/sosa/FeatureOfInterest");
        ExtendedIterator<OntClass> ontClassExtendedIterator = ontClass1.listEquivalentClasses();
        Set<String> otherSet = new HashSet<>();
        while (ontClassExtendedIterator.hasNext()) {
            System.out.println("========================================");
            OntClass o = ontClassExtendedIterator.next();
            System.out.println("class:" + o.toString());
            StmtIterator stmtIterator = o.listProperties();
            while (stmtIterator.hasNext()) {
                Statement next = stmtIterator.next();
                Resource resource = next.getResource();
                //System.out.println(next.getSubject() + " == " + next.getPredicate() + " == " + next.getObject());
                if (!next.getPredicate().toString().endsWith("onProperty")) {
                    System.out.println(next.getObject().toString());
                    otherSet.add(next.getObject().toString());
                }
            }
            System.out.println("========================================");
        }
        otherSet.add("http://www.w3.org/ns/sosa/locate");
        otherSet.add("http://www.w3.org/ns/sosa/hasFeatureOfInterest");
        otherSet.add("http://www.w3.org/ns/sosa/madeObservation");
        otherSet.add("http://www.w3.org/ns/sosa/is_property_of");
        otherSet.add("http://www.w3.org/ns/sosa/data_trans");
        otherSet.add("http://www.w3.org/ns/sosa/has_property");
        otherSet.add("http://www.w3.org/ns/sosa/connect");otherSet.add("http://www.w3.org/ns/sosa/haslocated");
        otherSet.add("http://www.w3.org/ns/sosa/isObservedBy");
        otherSet.add("http://www.w3.org/ns/sosa/observedProperty");
        otherSet.add("http://www.w3.org/ns/sosa/observes");
        otherSet.add("http://www.w3.org/ns/sosa/madeBySensor");
        otherSet.add("http://www.w3.org/ns/sosa/isFeatureOfInterestOf");


        System.out.println("========================================================");
        OntResource ontResource = ontModel.getOntResource("http://www.w3.org/ns/sosa/参考腔稳频激光器单元");
        OntResource ontResource1 = ontModel.getOntResource("http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1");
        System.out.println(ontResource.getClass());
        System.out.println(ontResource1.getClass());
        OntClass ontClass = ontModel.getOntClass("http://www.w3.org/ns/sosa/参考腔稳频激光器单元");
        Individual individual = ontModel.getIndividual("http://www.w3.org/ns/sosa/参考腔稳频激光器单元A1.1");
        System.out.println(ontClass.getClass());
        System.out.println(individual.getClass());

//        OntClass tmp = ontClass;
//        while (tmp != null) {
//            System.out.println(tmp.getURI());
//            tmp = tmp.getSuperClass();
//        }

        ExtendedIterator<OntProperty> ontPropertyExtendedIterator = ontClass.listDeclaredProperties();
        Set<String> set = new HashSet<>();
        while(ontPropertyExtendedIterator.hasNext()) {
            OntProperty next = ontPropertyExtendedIterator.next();
            if (!next.isAnnotationProperty() && !next.isInverseOf(ontModel.getProperty("http://www.w3.org/ns/sosa/ObservableProperty"))) {
                String s = next.toString();
                set.add(s);
            }
            //char c = s.charAt(s.length()-1);
            //if (!Character.isUpperCase(c) && !Character.isLowerCase(c)) {
            //    System.out.println("is not letter " + c);
                //set.add(s);
            //}

        }
        set.removeAll(otherSet);
        set.forEach(e -> System.out.println(e));
        System.out.println("=======================================================");
        OwlResourceData owlResourceData = OwlResourceUtil.parseResourceFile("D:\\Users\\liyi\\Desktop\\1\\实验室\\光纤授时\\西安.owl");
        OwlClass owlClass = owlResourceData.classMap.get(ontClass.getURI());
        System.out.println(owlClass.uri);
        Map<String, equivalent_class> map =  owlClass.equivalentClass;
        for (String s : map.keySet()) {
            System.out.println(map.get(s).euri + "  " + map.get(s).ValuesFrom +"  " +  map.get(s).onProperty);
        }
//        System.out.println("==========================");
//        Set<String> set2 = new HashSet<>();
//        OntClass ontClass1 = ontModel.getOntClass("http://www.w3.org/ns/sosa/FeatureOfInterest");
//        ExtendedIterator<OntProperty> ontPropertyExtendedIterator1 = ontClass1.listDeclaredProperties();
//        while(ontPropertyExtendedIterator1.hasNext()) {
//            set2.add(ontPropertyExtendedIterator1.next().toString());
//        }
//        set2.forEach(e -> System.out.println(e));
//        set.removeAll(set2);
//        set.forEach(e -> System.out.println(e));
//        StmtIterator stmtIterator = ontResource
//        while (stmtIterator.hasNext()) {
//            Statement next = stmtIterator.next();
//            System.out.println(next.getObject());
//            System.out.println(ontModel.getProperty(next.getObject().toString()));
//        }
    }
}
