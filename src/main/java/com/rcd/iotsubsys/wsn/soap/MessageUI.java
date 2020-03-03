package com.rcd.iotsubsys.wsn.soap;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//import org.apache.servicemix.wsn.push.NotificationProcessImpl;
//import wsn.wsnclient.command.SendWSNCommand;

public class MessageUI {
	private static String webserviceAddress = null;
	private static String wsnAddress = null;
	public static List<String> topics = new ArrayList<String>();
	private static Properties props;
	public static int faildCount;

	public static void main(String args[]) {
        //本机地址
		String wsnAddr = "http://ComplexEvent.168.100.12:9011/wsn-core";
		String receiveAddr = "http://ComplexEvent.168.100.12:9010/wsn-subscribe";
		SendWSNCommand receive = new SendWSNCommand(receiveAddr, wsnAddr);

		//设置用户id
		String id = "database";
		// 消息处理逻辑
		//UserNotificationProcessImpl implementor = new UserNotificationProcessImpl();
			// �?启接收服�?
		//Endpoint endpint = Endpoint.publish(receiveAddr, implementor);
		//receive.subscribe(id, "test3");

        //数据库表初始化部分在Configuration_Tool项目
		//数据库显示界面在Coolsql 项目


	}

}
