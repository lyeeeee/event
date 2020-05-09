package com.rcd.iotsubsys.wsn;

import com.rcd.iotsubsys.service.deduce.Deducer;
import com.rcd.iotsubsys.service.util.StringUtil;
import com.rcd.iotsubsys.util.SubscribeUtil;
import com.rcd.iotsubsys.wsn.soap.INotificationProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 客户端ws处理程序
 * 对应的是wsn层ws处理程序
 * 收到订阅消息进行处理
 */
//@WebService(endpointInterface = "com.rcd.iotsubsys.wsn.soap.INotificationProcess",
//    serviceName = "INotificationProcess")
public class UserNotificationProcessImpl implements INotificationProcess {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserNotificationProcessImpl.class);

    public Map<String, List<Deducer>> topicWithDeducer = new HashMap<>();


//    @Autowired
//    WarnService warnService;

    //private WarnService service;


    // 处理接收到的消息
    public String notificationProcess(String notification) {

        String topic = StringUtil.splitString(notification, "<topic>", "</topic>");

        String msg = StringUtil.splitString(notification, "<message>", "</message>");

        LOGGER.info("receive topic: {}", topic);
        LOGGER.info("receive message: {}", msg);

        if (topic.equals(SubscribeUtil.TOPIC_TELEMTRY)) {
            for (Deducer deducer : topicWithDeducer.get(SubscribeUtil.TOPIC_TELEMTRY)) {
                deducer.receiveMessage(msg);
            }
        }

        return topic;
    }




    public void registDeducer(Deducer deducer, String topic) {
        List<Deducer> tmp = topicWithDeducer.get(topic);
        if (tmp == null) tmp = new ArrayList<>();
        tmp.add(deducer);
    }
//    public static void main(String[] args) {
//        try {
//            Desktop.getDesktop().open(new File("C:\\z3exe\\z3.exe"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




    //        try {
//        try {
//            System.setProperty("java.awt.headless", "false");
//            Desktop.getDesktop().open(new File("C:\\z3exe\\z3.exe"));
//            Thread.sleep(10000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }   catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        if("1".equals(msg) || "2".equals(msg)){
//        System.out.println("------------------------"+msg);
//            getService().save(msg);//计算过程结束后，把type保存起来
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

}
