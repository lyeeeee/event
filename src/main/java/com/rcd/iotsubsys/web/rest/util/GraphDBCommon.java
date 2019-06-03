package com.rcd.iotsubsys.web.rest.util;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;

import java.io.File;

public class GraphDBCommon {
    public final static String GRAPHDB_SERVER = "http://192.168.2.138:7200/";
    public final static String REPOSITORY_ID = "IOTKnowledge";

    public static void main(String[] args) {
        Repository repository = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);
        repository.initialize();
        File file = new File("C:\\Users\\hyf_0\\Desktop\\owl\\aaa.owl");
        String baseURI = "http://IOTKnowledge/test";
        ValueFactory f = repository.getValueFactory();
        IRI context = f.createIRI("http://IOTKnowledge/test");
        RepositoryConnection con = repository.getConnection();
        try {
            con.add(file, baseURI, RDFFormat.RDFXML, context);
        } catch (Exception e) {

        }

    }

}
