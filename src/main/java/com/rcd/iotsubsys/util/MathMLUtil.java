package com.rcd.iotsubsys.util;

import com.alibaba.fastjson.JSONObject;
import com.microsoft.z3.*;
import com.rcd.iotsubsys.domain.event.FolumaKnowledge;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeSelectedFormula;
import com.rcd.iotsubsys.repository.event.KnowledgeFolumaRepository;
import com.rcd.iotsubsys.repository.event.KnowledgeSelectedFormulaRepository;
import org.apache.jena.sparql.function.library.context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2021-01-16 21:14
 */
public class MathMLUtil {

    private static final Logger logger = LoggerFactory.getLogger(MathMLUtil.class);

    public MathMLUtil(){}

    public BoolExpr getExprByMathML(List<Object[]> fomulas, Context context
        , Map<String, RealExpr> uilToExperMap
    ,Map<String, String> dataNameToUilMap) {

        BoolExpr ret = context.mkBool(true);

        for (Object[] f : fomulas) {
            BoolExpr be = null;
            String mathMl = ((FolumaKnowledge)f[1]).getFoluma();
            String varRelated = ((FolumaKnowledge)f[1]).getRelation();
            String[] relations= varRelated.trim().split("\n");
            logger.info("variables relation:{}", JSONObject.toJSONString(relations));
            if (mathMl.contains("sqrt")) {
                RealExpr x = uilToExperMap.get(relations[0].split("=")[1]);
                RealExpr y = uilToExperMap.get(relations[1].split("=")[1]);
                RealExpr z = uilToExperMap.get(relations[2].split("=")[1]);
                ArithExpr arithExpr = context.mkPower(x, context.mkInt(2));
                arithExpr = context.mkAdd(arithExpr, context.mkPower(y, context.mkInt(3)));
                arithExpr = context.mkSub(arithExpr, context.mkPower(z, context.mkInt(2)));
                String comparator = ((KnowledgeSelectedFormula)f[0]).getAttributeRelation();
                long value = Long.parseLong(((KnowledgeSelectedFormula)f[0]).getRelationValue());
                if (comparator.equals("0")) {
                    be = context.mkLe(arithExpr, context.mkPower(context.mkInt(value), context.mkInt(2)));
                } else if (comparator.equals("1")) {
                    be = context.mkLe(arithExpr, context.mkPower(context.mkInt(value), context.mkInt(2)));
                } else if (comparator.equals("2")) {
                    be = context.mkEq(arithExpr, context.mkPower(context.mkInt(value), context.mkInt(2)));
                } else if (comparator.equals("3")) {
                    be = context.mkGe(arithExpr, context.mkPower(context.mkInt(value), context.mkInt(2)));
                } else if (comparator.equals("4")) {
                    be = context.mkGt(arithExpr, context.mkPower(context.mkInt(value), context.mkInt(2)));
                }
                be = context.mkForall(new Expr[]{x, y, z}, be,1, null, null, null, null);
            }
            ret = context.mkAnd(ret, be);
        }
        logger.info("formula expr:{}", ret.toString());
        return ret;
    }
}
