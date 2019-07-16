package com.rcd.iotsubsys.wsn;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 向wsn发送订阅消息
 */
public class SendWSNCommand {
    private static int counter = 0;
    Logger logger = LoggerFactory.getLogger(SendWSNCommand.class);
    private String receiveAddr;
    private String wsnAddr;
    private String endpointAddr;
    private String localServiceAddr;
    private String subscriptionAddr;
    private HttpClient client;

    public SendWSNCommand(String receiveAddr, String wsnAddress) {
        localServiceAddr = receiveAddr;
        wsnAddr = wsnAddress;
        endpointAddr = null;
        client = new HttpClient();
    }

    /**
     * 将<、>切换为xml格式
     *
     * @param string
     * @return
     */
    public static String EscapeSequenceGenerate(String string) {
        string = string.replaceAll("<", "&lt;");
        string = string.replaceAll(">", "&gt;");
        return string;
    }

    /**
     * 订阅操作，时延和丢包率采用默认值
     *
     * @param id
     * @param topic
     * @return
     */
    public String subscribe(String id, String topic) {
        return subscribe(id, topic, localServiceAddr, Long.parseLong("1000"), Double.parseDouble("0.2"));
    }

    /**
     * 订阅相关主题
     *
     * @param topic
     * @return
     */
    public String subscribe(String id, String topic, String receiveAddress, long delay, double lostRate) {
        String content = "";
        String returnValue = "";

        content += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        content += "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:org=\"http://edu.bupt.wangfu.module.wsnMgr.util.soap\">";
        content += "<soapenv:Header/>";
        content += "<soapenv:Body>";
        content += "<org:WsnProcess>";
        content += EscapeSequenceGenerate(
            "<wsnt:Subscribe xmlns:wsnt=\"http://docs.oasis-open.org/wsnMgr/b-2\" xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">");
        content += EscapeSequenceGenerate("<wsnt:ConsumerReference>");
        content += EscapeSequenceGenerate(" <wsa:Address>");
        content += EscapeSequenceGenerate("<id>" + id + "</id>");
        content += EscapeSequenceGenerate("<topic>" + topic + "</topic>");
        content += EscapeSequenceGenerate("<userAddress>" + receiveAddress + "</userAddress>");
        content += EscapeSequenceGenerate("<delay>" + delay + "</delay>");
        content += EscapeSequenceGenerate("<lostRate>" + lostRate + "</lostRate>");
        content += EscapeSequenceGenerate("</wsa:Address>");
        content += EscapeSequenceGenerate("</wsnt:ConsumerReference>");
        content += EscapeSequenceGenerate("<wsnt:Filter>");
        content += EscapeSequenceGenerate("<wsnt:TopicExpression Dialect=\"http://docs.oasis-open.org/wsn/t-1/TopicExpression/Simple\">");
        content += EscapeSequenceGenerate(topic);
        content += EscapeSequenceGenerate("</wsnt:TopicExpression>");
        content += EscapeSequenceGenerate("</wsnt:Filter>");
        content += EscapeSequenceGenerate("<wsnt:SubscriberAddress>");
        content += EscapeSequenceGenerate("</wsnt:SubscriberAddress>");
        content += EscapeSequenceGenerate("</wsnt:Subscribe>");
        content += "</org:WsnProcess>";
        content += "</soapenv:Body>";
        content += "</soapenv:Envelope>";
        String[] responseValue = send(wsnAddr + "/wsnprocess/", new HashMap<String, String>(), "utf-8", true, content.trim());

        if (responseValue[0].equals("200") && !responseValue[1].contains("failed")) {
            returnValue = "ok";
            SubscribeResponse response = new SubscribeResponse();
            String message = responseValue[1];
            int messageStart = message.indexOf("<ns2:Address>") + 13;
            int messageEnd = message.indexOf("</ns2:Address>");
            if ((messageStart >= 0) && (messageEnd >= 0)) {
                String address = message.substring(messageStart, messageEnd);
                subscriptionAddr = address;
                logger.info("******************************subscribe Address " + subscriptionAddr);
            }
        } else {
            returnValue = "error";
        }
        return returnValue;
    }

    protected String[] send(String url, Map<String, String> params, String charset, boolean pretty, String content) {
        StringBuffer response = new StringBuffer();
//        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        method.setQueryString("");
        content = new String(content.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        //		method.setRequestBody(content);
        //log.info("content:   " + content);
        logger.info("content:   " + content);
        try {
            method.setRequestEntity(new StringRequestEntity(content, "text/xml", "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        if (params != null) {
            HttpMethodParams p = new HttpMethodParams();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                p.setParameter(entry.getKey(), entry.getValue());
            }
            method.setParams(p);
        }
        try {
            int status = client.executeMethod(method);
            counter++;
            logger.info("isresponsecounter:" + counter);
            logger.info("----------5");
            BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), charset));
            String line;
            logger.info("----------1");
            while ((line = reader.readLine()) != null) {
                logger.info("----------2");
                if (pretty) {
                    response.append(line).append(System.getProperty("line.separator"));
                } else {
                    response.append(line);
                }
            }
            logger.info("----------3");
            reader.close();
            method.releaseConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            method.releaseConnection();
        }
        logger.info("----------4");
        String res = response.toString();
        res = res.replaceAll("&lt;", "<");
        res = res.replaceAll("&gt;", ">");
        res = res.replaceAll("&quot;", "\"\"");
        logger.info("response:  " + res);
        return new String[]{String.valueOf(method.getStatusCode()), res};
    }
}
