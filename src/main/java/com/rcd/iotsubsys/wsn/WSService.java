package com.rcd.iotsubsys.wsn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Endpoint;

/**
 * @Author: HUHU
 * @Date: 2019/6/27 23:56
 * <p>
 * 发布与订阅主题
 */
@Service
@Transactional
public class WSService {
    private final Logger logger = LoggerFactory.getLogger(WSService.class);
    private String addr;


    // 获取订阅消息
    @Async
    public void getInfoByWSN() {
        //本机地址
        String wsnAddr = "http://192.168.99.19:9011/wsn-core";
        String receiveAddr = "http://192.168.99.19:9008/wsn-subscribe";
        SendWSNCommand receive = new SendWSNCommand(receiveAddr, wsnAddr);
        //设置用户id
        String id = "Fiber";
        // 消息处理逻辑
        UserNotificationProcessImpl implementor = new UserNotificationProcessImpl();
        // 启接收服
        Endpoint endpint = Endpoint.publish(receiveAddr, implementor);
        receive.subscribe(id, "test1");
    }

}
