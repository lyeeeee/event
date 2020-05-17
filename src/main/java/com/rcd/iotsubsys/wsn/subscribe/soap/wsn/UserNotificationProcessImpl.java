package com.rcd.iotsubsys.wsn.subscribe.soap.wsn;

import com.rcd.iotsubsys.service.deduce.Deducer;
import com.rcd.iotsubsys.service.util.StringUtil;
import com.rcd.iotsubsys.util.SubscribeUtil;
import com.rcd.iotsubsys.wsn.subscribe.soap.INotificationProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端ws处理程序
 * 对应的是wsn层ws处理程序
 */
@Service
@WebService(endpointInterface= "com.rcd.iotsubsys.wsn.subscribe.soap.INotificationProcess",
        serviceName="NotificationProcessImpl")
public class UserNotificationProcessImpl implements INotificationProcess {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserNotificationProcessImpl.class);

    public static ConcurrentHashMap<String, List<Deducer>> topicWithDeducer = new ConcurrentHashMap<>();

    @Override
    public String  notificationProcess(String notification) {
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

    public synchronized void registDeducer(Deducer deducer, String topic) {
        List<Deducer> tmp = topicWithDeducer.get(topic);
        if (tmp == null) tmp = new ArrayList<>();
        tmp.add(deducer);
        topicWithDeducer.put(topic, tmp);
    }

    public synchronized void deRegisterDeducer(Deducer deducer, String topic) {
        List<Deducer> tmp = topicWithDeducer.get(topic);
        if (tmp != null) {
            tmp.remove(deducer);
        }
    }
}
