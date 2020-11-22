package com.rcd.iotsubsys;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Model;
import com.microsoft.z3.Solver;
import org.apache.solr.client.solrj.SolrClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-10-05 11:02
 */
public class TestCnfFile {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("D:\\Users\\liyi\\Desktop\\1\\实验室\\z3数据\\Test\\Test\\test.cnf");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Context context = new Context();
        Solver solver = context.mkSolver();
        BoolExpr x1 = context.mkBoolConst("x1");
        BoolExpr x2 = context.mkBoolConst("x2");
        BoolExpr x3 = context.mkBoolConst("x3");
        solver.add(context.mkOr(x1, x2));
        solver.add(context.mkOr(context.mkNot(x1), context.mkNot(x2)));
        solver.add(context.mkEq(x1, context.mkBool(true)));
        solver.check();
        BoolExpr[] assertions = solver.getAssertions();
        for (BoolExpr assertion : assertions) {
            System.out.println(assertion);
        }
        Model model = solver.getModel();
        System.out.println(model);
        System.out.println(model.getConstInterp(x1));
    }
}
