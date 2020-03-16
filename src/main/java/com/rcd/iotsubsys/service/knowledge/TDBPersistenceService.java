package com.rcd.iotsubsys.service.knowledge;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-14 20:47
 */
public class TDBPersistenceService {

    public static final Logger LOGGER = LoggerFactory.getLogger(TDBPersistenceService.class);

    public Dataset dataset = TDBFactory.createDataset("name");

    /**
     * 建立TDB数据文件夹；
     */
    public TDBPersistenceService() {
    }

    /**
     * 将rdf文件加载到model中；
     */
    public void loadModel(String modelName, String rdfFilePath, Boolean isOverride) {

        int result;
        Model model = null;
        dataset.begin(ReadWrite.WRITE);
        try {
            //已有同名model，且不需要使用新的三元组覆盖旧TDB文件；
            if (dataset.containsNamedModel(modelName) && (!isOverride)) {
                result = 1;
            }
            //没有同名model，或者有同名文件需要覆盖；
            else {
                if (dataset.containsNamedModel(modelName))
                    result = 2;
                else
                    result = 3;
                //移除已有的model；
                dataset.removeNamedModel(modelName);
                //建立一个新的TDB Model，一个TDB可以有多个model，类似数据库的多个表；
                model = dataset.getNamedModel(modelName);
                //事务开始；
                model.begin();
                //读取RDF文件到model中；
                FileManager.get().readModel(model, rdfFilePath);
                //将事务提交；
                model.commit();
                //务必记得将dataset的事务提交，否则无法完成增删改查操作；
                dataset.commit();
            }
        } catch (Exception e) {
            LOGGER.error(e.toString());
            result = 0;
        } finally {
            if (model != null && !model.isEmpty())
                model.close();
            dataset.end();
        }
        switch (result) {
            case 0:
                LOGGER.error(modelName + "：读取model错误！");
                break;
            case 1:
                LOGGER.info(modelName + "：已有该model，不需要覆盖！");
                break;
            case 2:
                LOGGER.info(modelName + "：已有该model，覆盖原TDB文件，并建立新的model！");
                break;
            case 3:
                LOGGER.info(modelName + "：建立新的TDB model！");
                break;
        }
    }

    /**
     * 删除Dataset中的某个model；
     */
    public void removeModel(String modelName) {
        if (!dataset.isInTransaction())
            dataset.begin(ReadWrite.WRITE);
        try {
            dataset.removeNamedModel(modelName);
            dataset.commit();
            LOGGER.info(modelName + "：已被移除!");
        } finally {
            dataset.end();
        }
    }

    /**
     * 关闭TDB连接；
     */
    public void closeTDB() {
        dataset.close();
    }

    /**
     * 判断Dataset中是否存在model；
     */
    public boolean findTDB(String modelName) {
        boolean result;
        dataset.begin(ReadWrite.READ);
        try {
            if (dataset.containsNamedModel(modelName))
                result = true;
            else
                result = false;
        } finally {
            dataset.end();
        }
        return result;
    }

    /**
     * 列出Dataset中所有model；
     */
    public List<String> listModels() {
        dataset.begin(ReadWrite.READ);
        List<String> uriList = new ArrayList<>();
        try {
            Iterator<String> names = dataset.listNames();
            String name;
            while (names.hasNext()) {
                name = names.next();
                uriList.add(name);
            }
        } finally {
            dataset.end();
        }
        return uriList;
    }

    /**
     * 获得Dataset中某个model；
     */
    public Model getModel(String modelName) {

        Model model;
        dataset.begin(ReadWrite.READ);
        try {
            model = dataset.getNamedModel(modelName);
        } finally {
            dataset.end();
        }
        return model;
    }

    /**
     * 获取默认模型；
     */
    public Model getDefaultModel() {
        dataset.begin(ReadWrite.READ);
        Model model;
        try {
            model = dataset.getDefaultModel();
            dataset.commit();
        } finally {
            dataset.end();
        }
        return model;
    }

}
