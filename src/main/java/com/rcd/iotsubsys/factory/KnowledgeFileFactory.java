package com.rcd.iotsubsys.factory;

import com.rcd.iotsubsys.config.Constant;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-22 10:20
 */
public class KnowledgeFileFactory {

    public static void fillPropertiesKnowledgeFile(KnowledgeFile file, Map<String, Object> map) {
        file.setModelName((String) map.get("name"));
        file.setDbPath(Constant.TDB_BASE_DIR);
        file.setTdbModelName((String) map.get("name"));
        MultipartFile multipartFile = (MultipartFile) map.get("file");
        file.setFileName(multipartFile.getOriginalFilename());
        file.setFilePath(Constant.ONTOLOGY_FILE_DIR);
        file.setGraphId((String) map.get("graphName"));
        file.setKnowledgeSynopsis((String) map.get("knowledgeSynopsis"));
    }
}
