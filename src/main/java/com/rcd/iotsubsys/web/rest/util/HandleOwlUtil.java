package com.rcd.iotsubsys.web.rest.util;

import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandleOwlUtil {
    public final static String GRAPHDB_SERVER = "http://10.255.4.233:7200/";
    //    public final static String GRAPHDB_SERVER = "http://192.168.2.138:7200/";
    public final static String REPOSITORY_ID = "IOTKnowledge";


    //1、根据NAMED_INDIVIDUAL查出符合条件的
    public final static String NAMED_INDIVIDUAL = "<http://www.w3.org/2002/07/owl#NamedIndividual>";
    public final static String LOCATE = "<http://www.w3.org/ns/sosa/locate>";
    public final static String TYPE = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>";
    public final static String SUB_CLASS_OF = "<http://www.w3.org/2000/01/rdf-schema#subClassOf>";
    public final static String HAS_PROPERTY = "<http://www.w3.org/ns/sosa/has_property>";


    /**
     * 根据
     * <http://www.w3.org/2002/07/owl#NamedIndividual>
     * 查询出符合条件的
     *
     * @return
     */
    public static List<Map<String, Object>> selectNamedIndividual() {
        String namedIndividual = "SELECT ?s ?p ?src FROM NAMED <http://csshi> where { Graph ?src{ ?s ?p " + NAMED_INDIVIDUAL + " }}";
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, namedIndividual);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    Map<String, Object> map = new HashMap<>();
                    BindingSet bindingSet = result.next();
                    map.put("p", bindingSet.getValue("p").stringValue());
                    map.put("s", bindingSet.getValue("s").stringValue());
                    map.put("src", bindingSet.getValue("src").stringValue());
                    resultList.add(map);
                }
            }

        }
        return resultList;
    }

    /**
     * 根据LOCATE查询判断是否为设备，存在则为设备
     *
     * @param s
     * @return
     */
    public static List<Map<String, Object>> selectEquipInfo(String s) {
        String namedIndividual = "SELECT ?o ?src FROM NAMED <http://csshi> where { Graph ?src{ <" + s + ">" + LOCATE + " ?o }}";
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, namedIndividual);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    Map<String, Object> map = new HashMap<>();
                    BindingSet bindingSet = result.next();
                    map.put("o", bindingSet.getValue("o").stringValue());
//                    map.put("s",bindingSet.getValue("s").stringValue());
//                    map.put("src",bindingSet.getValue("src").stringValue());
                    resultList.add(map);
                }
            }

        }
        return resultList;
    }

    /**
     * 获取属性
     *
     * @param s
     * @return
     */
    public static List<Map<String, Object>> selectAttribute(String s) {
        String sparql = "SELECT ?o ?src FROM NAMED <http://csshi> where { Graph ?src{ <" + s + ">" + HAS_PROPERTY + " ?o }}";
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();
        List<Map<String, Object>> resultList = new ArrayList<>();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    Map<String, Object> map = new HashMap<>();
                    BindingSet bindingSet = result.next();
                    map.put("o", bindingSet.getValue("o").stringValue());
                    resultList.add(map);
                }
            }

        }
        return resultList;
    }


    public static String selectSubsystem(String s) {
        String nextS = "";
        String resultS = "";
        String sparql = "SELECT ?o ?src FROM NAMED <http://csshi> where { Graph ?src{ <" + s + ">" + TYPE + " ?o FILTER (?o != " + NAMED_INDIVIDUAL + ") }}";
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();
//        List<Map<String,Object>> resultList = new ArrayList<>();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {  // iterate over the result
                    BindingSet bindingSet = result.next();
                    nextS = bindingSet.getValue("o").stringValue();
                }
                if (nextS == null || "".equals(nextS)) {
                    return "";
                }
            }
        }
        resultS = selectSubSystem2(nextS);

        return resultS;
    }

    public static String selectSubSystem2(String s) {
        String nexts = "";
        String sparql = "SELECT distinct ?o  ?src FROM NAMED <http://ceshi612> where { Graph ?src{ <" + s + "> " + SUB_CLASS_OF + " ?o}}";
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        RepositoryConnection con = repository.getConnection();

        try (RepositoryConnection conn = repository.getConnection()) {
            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, sparql);
            try (TupleQueryResult result = tupleQuery.evaluate()) {
                while (result.hasNext()) {
                    BindingSet bindingSet = result.next();
                    nexts = bindingSet.getValue("o").stringValue();
                }
            }
        }
        if (nexts == null || "".equals(nexts)) {
            return "";
        }
        if ("http://www.w3.org/ns/sosa/FeatureOfInterest".equals(nexts)) {
            return s;
        }
        return selectSubSystem2(nexts);
    }

    public static List<Map<String, Object>> HandleOwl() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        //获取所有的namedIndividual后遍历处理
        List<Map<String, Object>> namedIndividual = selectNamedIndividual();
        for (Map<String, Object> namedIndividualMap : namedIndividual) {

            //以当前的s作为主语，locate作谓语，查询是否存在，存在则为设备
            List<Map<String, Object>> locateList = selectEquipInfo(namedIndividualMap.get("s").toString());
            if (locateList == null || locateList.size() == 0) {//为空则不是设备，跳出进行下一次循环
                continue;
            }
            //不为空则是设备
            //获取属性
            List<Map<String, Object>> attributeList = selectAttribute(namedIndividualMap.get("s").toString());
            for (Map<String, Object> attributeMap : attributeList) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("src", namedIndividualMap.get("src"));//设备
                resultMap.put("shebei", namedIndividualMap.get("s"));//设备
                resultMap.put("zizhan", locateList.get(0).get("o"));//子站
                resultMap.put("zixitong", selectSubsystem(namedIndividualMap.get("s").toString()));//子系统
                resultMap.put("attribute", attributeMap.get("o"));//属性

                resultList.add(resultMap);
            }
        }
        return resultList;
    }

//    public static void main(String[] args) {
//        HandleOwl();
//    }
}
