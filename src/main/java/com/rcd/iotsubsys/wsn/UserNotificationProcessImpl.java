package com.rcd.iotsubsys.wsn;

import com.rcd.iotsubsys.service.warn.WarnService;
import com.rcd.iotsubsys.wsn.soap.INotificationProcess;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 客户端ws处理程序
 * 对应的是wsn层ws处理程序
 * 收到订阅消息进行处理
 */
@Service

@WebService(endpointInterface = "com.rcd.iotsubsys.wsn.soap.INotificationProcess",
    serviceName = "INotificationProcess")
public class UserNotificationProcessImpl implements INotificationProcess {
    //Logger logger = LoggerFactory.getLogger(UserNotificationProcessImpl.class);
//    @Autowired
//    WarnService warnService;
    private WarnService service;
    private WarnService getService(){
        if(this.service == null){
            this.service = SpringContextUtil.getContext().getBean(WarnService.class);
        }
        return this.service;
    }

//    public UserNotificationProcessImpl(WarnService warnService) {
//        this.warnService = warnService;
//    }


    // 处理接收到的消息
    public String notificationProcess(String notification) {
        System.out.println("receive message"+notification);
        String topic = splitString(notification, "<topic>", "</topic>");
        String msg = splitString(notification, "<message>", "</message>");
        //logger.info("收到的数据" + notification);
        //logger.info("收到订阅主题 " + topic + " 文本消息：" + msg);
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
            "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">" + msg + "</xs:schema>";
//        try {
        try {
            System.setProperty("java.awt.headless", "false");
            Desktop.getDesktop().open(new File("C:\\z3exe\\z3.exe"));
            Thread.sleep(10000);
        } catch (IOException e) {
            e.printStackTrace();
        }   catch (InterruptedException e) {
            e.printStackTrace();
        }
//        if("1".equals(msg) || "2".equals(msg)){
        System.out.println("------------------------"+msg);
            getService().save(msg);//计算过程结束后，把type保存起来
//        }
//        else {
//            getService().save("0");
//        }
//        getService().save(msg);
//        System.out.println("---------" + msg);

//            Document document = DocumentHelper.parseText(xml);
//            Element root = document.getRootElement(); // 获取根节点
//            // logger.info("根节点：" + root.getName()); // 拿到根节点的名称
//            // 获取子节点
//            // 获取设备节点
//            Element resource = root.element("resource");
//            // 获得警告节点 dom4j
//            Element alarm = root.element("alarm");
//
//            resource.elementText("site_name");
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }

        return topic;
    }

    public String splitString(String string, String start, String end) {
        int from = string.indexOf(start) + start.length();
        int to = string.indexOf(end);
        return string.substring(from, to);
    }


//    public static void main(String[] args) {
//        try {
//            Desktop.getDesktop().open(new File("C:\\z3exe\\z3.exe"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
