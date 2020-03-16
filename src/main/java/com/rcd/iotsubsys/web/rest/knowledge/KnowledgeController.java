package com.rcd.iotsubsys.web.rest.knowledge;

import com.rcd.iotsubsys.dto.response.JsonResult;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-14 16:37
 */
@RestController
@RequestMapping("api/knowledge")
public class KnowledgeController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public JsonResult<Object> uploadKnowledgeFile(
        @RequestParam("fileList")MultipartFile[] fileList, Map<String,Object> map) {
        for (MultipartFile multipartFile : fileList) {
            System.out.println(multipartFile);
        }
        map.values().forEach(a -> System.out.println(a));
        Dataset dataset = TDBFactory.createDataset();
        Model defaultModel = ModelFactory.createDefaultModel();
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //创建dataset和model
        Dataset dataset = TDBFactory.createDataset("name");
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
        dataset.begin(ReadWrite.READ);
        Model test1 = dataset.getNamedModel("test1");

        OntModelSpec spec;
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, test1);
        ExtendedIterator<OntClass> ontClassExtendedIterator = model.listClasses();
        while (ontClassExtendedIterator.hasNext()) {
            System.out.println(ontClassExtendedIterator.next());
        }
    }
}
