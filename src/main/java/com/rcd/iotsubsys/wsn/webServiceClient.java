package com.rcd.iotsubsys.wsn;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: HUHU
 * @Date: 2019/6/27 18:53
 */

@Component
public class webServiceClient {
    static Logger logger = LoggerFactory.getLogger(webServiceClient.class);

    /**
     * @Postcontruct’在依赖注入完成后自动调用
     */
    @PostConstruct
    public static void start() {
        logger.info("启动wsn客户端，等待订阅消息");
        WSService service = new WSService();
//        service.getInfoByWSN();
    }
}
