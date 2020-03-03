package com.rcd.iotsubsys.wsn;
import com.rcd.iotsubsys.wsn.soap.SendWSNCommand;

import javax.xml.ws.Endpoint;
public class TestWsnSDB {
    public static void main(String[] args) {
        String wsnAddr = "http://192.168.99.121:9011/wsn-core";
        String receiveAddr = "http://192.168.99.121:9022/wsn-subscribe";

        // 消息处理逻辑
        UserNotificationProcessImpl implementor = new UserNotificationProcessImpl();
        // 启接收服
        Endpoint endpint = Endpoint.publish(receiveAddr, implementor);

        SendWSNCommand receive = new SendWSNCommand(receiveAddr, wsnAddr);
        //设置用户id
        String id = "adminEvent";
        receive.subscribe(id, "event");
    }
}
