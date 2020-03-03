package com.rcd.iotsubsys.web.rest.util;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDBCommon {
    public final static String GRAPHDB_SERVER = "http://192.168.99.16:7200/";
//    public final static String GRAPHDB_SERVER = "http://192.168.2.95:7200/";
    public final static String REPOSITORY_ID = "IOTKnowledge";

    public static void insertGraphDB(String graphName,File file) {
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
//        File file = new File("C:\\Users\\hyf_0\\Desktop\\owl\\aaa.owl");
//        String baseURI = "http://IOTKnowledge/test";
        String baseURI = graphName;
        ValueFactory f = repository.getValueFactory();
        IRI context = f.createIRI(graphName);
        RepositoryConnection con = repository.getConnection();
        try {
            con.add(file, baseURI, RDFFormat.RDFXML, context);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //根据图名查询
    public static List<Map<String,Object>> selectGraphDBByGraphName(String GraphName){
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();
        ValueFactory f = repository.getValueFactory();
//        IRI context = f.createIRI("http://0605b");
        IRI context = f.createIRI(GraphName);
        List<Map<String,Object>> resultList = new ArrayList<>();
        try (RepositoryResult<Statement> result = con.getStatements(null, null, null, context)) {
            while (result.hasNext()) {
                Map<String,Object> map = new HashMap<>();
                Statement st = result.next();
                // do something interesting with the result
//                map.put("s",st.getSubject());
                map.put("attribute",st.getObject().stringValue());
                map.put("equipment",st.getPredicate().stringValue());
                map.put("graphName",st.getContext().stringValue());
                resultList.add(map);
            }
        }
        return resultList;
    }

    //根据sparql查询
    public static List<Map<String,Object>> selectGraphDB(String sparql){
//        String sparql = "SELECT  ?s ?p ?o ?src FROM NAMED <http://csshi> where { Graph ?src{ ?s ?p ?o }}";
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();
        List<Map<String,Object>> resultList = new ArrayList<>();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    Map<String,Object> map = new HashMap<>();
                    BindingSet bindingSet = result.next();
                    map.put("o",bindingSet.getValue("o").stringValue());
                    map.put("p",bindingSet.getValue("p").stringValue());
                    map.put("s",bindingSet.getValue("s").stringValue());
                    map.put("src",bindingSet.getValue("src").stringValue());
                    resultList.add(map);
                }
            }

        }
        return resultList;
    }
    public static List<Map<String,Object>> selectGraphDB1(String sparql){
//        String sparql = "SELECT  ?s ?p ?o ?src FROM NAMED <http://csshi> where { Graph ?src{ ?s ?p ?o }}";
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();
        List<Map<String,Object>> resultList = new ArrayList<>();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    Map<String,Object> map = new HashMap<>();
                    BindingSet bindingSet = result.next();
                    map.put("o",bindingSet.getValue("o").stringValue());
                    map.put("p",bindingSet.getValue("p").stringValue());
                    map.put("src",bindingSet.getValue("src").stringValue());
                    resultList.add(map);
                }
            }

        }
        return resultList;
    }



//    public static void main (String[] args) {
//        String sparql = "SELECT  ?s ?p ?o ?src FROM NAMED <http://0605a> where { Graph ?src{ ?s ?p ?o }}";
//
//        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
//        repository.initialize();
//        RepositoryConnection con = repository.getConnection();
//        List<Map<String,Object>> resultList = new ArrayList<>();
//
//        try (RepositoryConnection conn = repository.getConnection()) {
//            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparql);
//            try (TupleQueryResult result = tupleQuery.evaluate()) {
//                while (result.hasNext()) {  // iterate over the result
//                    Map<String,Object> map = new HashMap<>();
//                    BindingSet bindingSet = result.next();
//                    map.put("o",bindingSet.getValue("o").stringValue());
//                    map.put("p",bindingSet.getValue("p").stringValue());
//                    map.put("s",bindingSet.getValue("s").stringValue());
//                    map.put("src",bindingSet.getValue("src").stringValue());
//                    resultList.add(map);
//                }
//            }
//            System.out.println(resultList);
//        }
//
//    }

}
