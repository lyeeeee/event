package com.rcd.iotsubsys.wsn.subscribe.soap; /**
 * Created by IntelliJ IDEA.
 * User: 15373
 * Date: 2020/4/1
 */

/**
 * Created by-logan on 2020/4/1.
 */
public class CaseSubscirbe {
    private Trans trans;
	//wsn程序中的wsn地址
    public static String wsnAddr = "http://127.0.0.1:9011/wsn-core";
    //    //	//保证端口要和其他订阅程序不一致。
	//接受的消息在soap包的UserNotificationProcessImpl.java的notificationProcess方法
    public static String sendAddr = "http://127.0.0.1:9009/wsn-subscribe";
    public CaseSubscirbe(String sendAddr,String wsnAddr,String topic)
    {
        trans = new Trans(sendAddr,wsnAddr,topic);
    }
    public void subscibe()
    {
        trans.subscribe();
    }

    public static void main(String[] args) {
		//CaseSubscirbe param 1:订阅地址 param 2：wsn地址 param 3:订阅主题名
        CaseSubscirbe sub = new CaseSubscirbe(sendAddr,wsnAddr,"telemetry");
		//订阅主题
        sub.subscibe();
    }
}
