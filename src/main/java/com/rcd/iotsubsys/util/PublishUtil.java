package com.rcd.iotsubsys.util;

import com.rcd.iotsubsys.wsn.publish.soap.Trans;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-05-08 22:10
 */
public class PublishUtil {

    private static int port = StringUtils.isEmpty(System.getProperty("")) ? 9030:Integer.parseInt(System.getProperty("publish_send_addr_port"));

    //wsn程序中的地址
    public static final String WSN_ADDR = "http://" + System.getProperty("publish_wsn_addr") + "/wsn-core";
    //sendAddr中保证不和其他发布程序的端口冲突
    public static final String SEND_ADDR_PREFIX = "http://"+ System.getProperty("publish_send_addr_ip") +":";

    private static final String SEND_ADDR_SUFFIX = "/wsn-send";

    private static Map<String, Trans> tansMap = new HashMap<>();

    public static final String DEVICE_FAILURE_MORE = "device_failure_more";

    public static final String LINK_FAILURE = "link_failure";

    public static synchronized void publish(String topic, String msg) {
        if (tansMap.containsKey(topic)) {
            tansMap.get(topic).sendMethod(msg);
        } else {
            Trans trans = new Trans(WSN_ADDR, SEND_ADDR_PREFIX + port++ + SEND_ADDR_SUFFIX, topic);
            trans.sendMethod(msg);
            tansMap.put(topic, trans);
        }
    }
}
