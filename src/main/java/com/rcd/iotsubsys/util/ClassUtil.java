package com.rcd.iotsubsys.util;

import com.rcd.iotsubsys.domain.deduce.pojo.Event;
import com.rcd.iotsubsys.domain.event.MetaEventAttrRelation;
import com.rcd.iotsubsys.domain.knowledge.KnowledgeMetaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-27 11:14
 */
public class ClassUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);


    /**
     * 批量生成Class
     * */
    public static Map<String, Class<?>> generateMetaEventClassWithAttrRelation(Map<KnowledgeMetaEvent, List<MetaEventAttrRelation>> eventWithAttrRelation) {

        Map<String, Class<?>> classReturn = new HashMap<>();

        for (KnowledgeMetaEvent metaEvent : eventWithAttrRelation.keySet()) {

            String topicName = metaEvent.getTopic().toUpperCase() + "Event";

            StringBuilder contentEventClass = new StringBuilder();

            StringBuilder attributes = new StringBuilder();

            StringBuilder prop = new StringBuilder();

            StringBuilder methodGet = new StringBuilder();

            StringBuilder methodSet = new StringBuilder();

            List<MetaEventAttrRelation> relations = eventWithAttrRelation.get(metaEvent);
            for (int i = 0; i < relations.size(); ++i) {

                MetaEventAttrRelation mear = relations.get(i);

                String aName = mear.getTopicAttribute();
                String aType = mear.getTopicAttributeType();

                if (aType.toUpperCase().contains("STRING")) {
                    aType = "String";
                } else if (aType.toUpperCase().contains("INT")) {
                    aType = "int";
                } else if (aType.toUpperCase().contains("DOUBLE")) {
                    aType = "double";
                } else if (aType.toUpperCase().contains("FLOAT")) {
                    aType = "float";
                }

                attributes.append("\tpublic ").append(aType).append(" ").append(aName).append(";\r\n");

                prop.append(aType).append(" ").append(aName).append("; ");

                methodGet.append("\tpublic ").append(aType).append(" get")
                    .append(aName.replaceFirst(aName.substring(0, 1), aName.substring(0, 1).toUpperCase()))
                    .append("(){\r\n\treturn ")
                    .append(aName)
                    .append(";\r\n\t}\r\n");

                methodSet.append("\tpublic ").append("void").append(" set")
                    .append(aName.replaceFirst(aName.substring(0, 1), aName.substring(0, 1).toUpperCase()))
                    .append("(").append(aType).append(" ").append(aName).append("){\n")
                    .append("\t\tthis.").append(aName).append(" = ").append(aName)
                    .append(";\r\n\t}\r\n");
            }

            contentEventClass.append("package com.rcd.iotsubsys.domain.deduce.pojo;\r\nimport com.rcd.iotsubsys.domain.deduce.pojo.Event;\r\n")
                .append("public class ").append(topicName).append(" implements Event{\r\n")
                .append(attributes).append(methodGet).append(methodSet)
                .append("}");

            //写入文件中
            //String fileName = Event.class.getResource("").getPath() + topicName + ".java";
            String fileName = "./src/main/java/com/rcd/iotsubsys/domain/deduce/pojo/" + topicName + ".java";
            FileWriter fw;

            try {
                fw = new FileWriter(fileName);
                PrintWriter pw = new PrintWriter(fw);
                pw.write(contentEventClass.toString());
                pw.close();
                fw.close();
                LOGGER.info("generate event java file: {}", fileName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String fullName = "com.rcd.iotsubsys.domain.deduce.pojo." + topicName;
            Class<?> eventClazz = compile(fileName, fullName);
            classReturn.put(metaEvent.getTopic(), eventClazz);
        }
        return classReturn;
    }

    /**
     * 编译Java文件
     * */
    public static Class<?> compile(String filePath, String packageName){

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 建立DiagnosticCollector对象
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(
            diagnostics, null, null);
        // 建立用于保存被编译文件名的对象
        // 每个文件被保存在一个从JavaFileObject继承的类中
        Iterable sourcefiles = fileManager.getJavaFileObjects(filePath);
        // 指定编译文件存放位置,如果不指定的话，编译的文件会和java源文件在一个文件夹中
        // 这样的话加载类的时候会报java.lang.ClassNotFoundException
        ClassLoader dummyc= Event.class.getClassLoader();
        URLClassLoader urlClassLoader=(URLClassLoader)dummyc;
        URL[] urls=urlClassLoader.getURLs();
        String classpath = "";
        for (URL i : urls) {
            classpath += ";" + i.getPath().substring(1);
        }
        Iterable<String> options = Arrays.asList("-classpath",System.getProperty("java.class.path")
            + classpath ,"-d", "./out/production/classes/");
        compiler.getTask(null, fileManager, diagnostics, options, null,
            sourcefiles).call();
        try {
            fileManager.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            Class<?> aClass = Class.forName(packageName);
            return aClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("can't find target class, check source file and class file");
        }
        return null;
    }
}
