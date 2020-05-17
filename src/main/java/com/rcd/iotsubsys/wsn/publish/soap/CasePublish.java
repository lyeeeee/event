package com.rcd.iotsubsys.wsn.publish.soap; /**
 * Created by IntelliJ IDEA.
 * User: 15373
 * Date: 2020/4/1
 */

/**
 * Created by-logan on 2020/4/1.
 */
public class CasePublish {
    private Trans trans;
	//wsn程序中的地址
    public static String wsnAddr = "http://127.0.0.1:9011/wsn-core";
	//sendAddr中保证不和其他发布程序的端口冲突
    public static String sendAddr = "http://127.0.0.1:9019/wsn-send";
    public CasePublish(String sendAddr,String wsnAddr,String topic)
    {
        trans = new Trans(sendAddr,wsnAddr,topic);
    }
    public void register()
    {
        trans.register();
    }
    public void publishmsg(String msg)
    {
        trans.sendMethod(msg);
    }

    /*
    <layer-1 name="device_failure"/>
    <layer-1 name="device_failure_judge"/>
    <layer-1 name="device_failure_person_arrive"/>
    <layer-1 name="device_failure_repair"/>
    <layer-1 name="device_failure_check"/>
    <layer-1 name="device_failure_system"/>
    <layer-1 name="device_failure_result"/>
     */
    public static void main(String[] args) throws InterruptedException {
		//CasePublish构造参数 param 1:wsn地址 param 2:发布地址 param 3:发布主题名
        String msg1 = "<resource><tableName>telemetry</tableName><siteName>西安</siteName>" +
            "<deviceName>EDFA设备</deviceName><dataName>输入光功率</dataName></resource>" +
            "<value><detected_value>-20.0</detected_value><type>float</type>" +
            "<timestamp>2020/05/16 21:53:18.292</timestamp></value>";

        String msg2 = "<resource><tableName>telemetry</tableName><siteName>西安</siteName>" +
            "<deviceName>EDFA设备</deviceName><dataName>输入光功率</dataName></resource>" +
            "<value><detected_value>15.0</detected_value><type>float</type>" +
            "<timestamp>2020/05/16 21:53:18.292</timestamp></value>";

        String msg3 = "<resource><tableName>telemetry</tableName><siteName>西安</siteName>" +
            "<deviceName>EDFA设备</deviceName><dataName>输出光功率</dataName></resource>" +
            "<value><detected_value>2.0</detected_value><type>float</type>" +
            "<timestamp>2020/05/16 21:53:18.292</timestamp></value>";

//        String msg1 = "<resource><site_name>" + "北京" + "</site_name>" +
//            "<device_name>" + "EDFA设备" +"</device_name>" +
//            "<data_name>" + "输入光功率" + "</data_name>" + "</resource>" +
//            "<value><value>" + "-14.0" + "</value>" +
//            "<type>" + "fault_type" + "</type>" +
//            "<timestamp>" + "time" + "</timestamp></value>";

//        String msg3 = "<resource><site_name>" + "北京" + "</site_name>" +
//            "<device_name>" + "EDFA设备" +"</device_name>" +
//            "<data_name>" + "输入光功率" + "</data_name>" + "</resource>" +
//            "<value><value>" + "-17.0" + "</value>" +
//            "<type>" + "fault_type" + "</type>" +
//            "<timestamp>" + "time" + "</timestamp></value>";

//        String msg2 = "<resource><site_name>" + "北京" + "</site_name>" +
//            "<device_name>" + "EDFA设备" +"</device_name>" +
//            "<data_name>" + "输出光功率" + "</data_name>" + "</resource>" +
//            "<value><value>" + "2.0" + "</value>" +
//            "<type>" + "fault_type" + "</type>" +
//            "<timestamp>" + "time" + "</timestamp></value>";
        CasePublish pub = new CasePublish(wsnAddr,sendAddr, "telemetry");
        //发布主题
        pub.register();
        //发布消息
        while (true) {
            for (int i = 0;i < 1; ++i) {
                pub.publishmsg(msg1);
                pub.publishmsg(msg3);
                System.out.println("send");
            }
            Thread.sleep(60*1000);
        }
    }
}
