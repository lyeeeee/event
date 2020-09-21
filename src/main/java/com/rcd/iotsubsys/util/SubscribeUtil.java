package com.rcd.iotsubsys.util;

import com.rcd.iotsubsys.wsn.subscribe.soap.Trans;

import java.util.Map;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-04-27 22:40
 */
public class SubscribeUtil {

    private static final String WSN_ADDR = "http://192.168.253.1:9011/wsn-core";//固定，发布订阅机器ip

    private static final String SENT_ADDR = "http://192.168.253.11:9020/wsn-subscribe";//发布订阅的机器ip，端口不能重复


    public static final String TOPIC_TELEMTRY = "data";


    public static void subscribe(String topic) {
        Trans trans = new Trans(SENT_ADDR,WSN_ADDR,topic);
        trans.subscribe();
    }
}
