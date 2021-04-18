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

    // 当前默认为
    private static final String WSN_ADDR = "http://"+ System.getProperty("subscribe_wsn_addr")+ "/wsn-core";//固定，发布订阅机器ip
    // 当前默认为
    private static final String SENT_ADDR = "http://"+ System.getProperty("subscribe_send_addr") +"/wsn-subscribe";//发布订阅的机器ip，端口不能重复


    public static final String TOPIC_TELEMTRY = "data";


    public static void subscribe(String topic) {
        Trans trans = new Trans(SENT_ADDR,WSN_ADDR,topic);
        trans.subscribe();
    }
}
