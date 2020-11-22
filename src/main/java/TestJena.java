import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;

import static org.apache.jena.ontology.OntModelSpec.OWL_DL_MEM_RULE_INF;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-10-19 16:46
 */
public class TestJena {

    public static void main(String[] args) {
        OntModelSpec spec;
        OntModel model = ModelFactory.createOntologyModel(OWL_DL_MEM_RULE_INF);
        model.read("D:\\Users\\liyi\\Desktop\\1\\实验室\\光纤授时\\西安.owl");
        Reasoner reasoner = model.getReasoner();
        //reasoner.
    }
}
