package com.rcd.iotsubsys.service.deduce;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2021-01-26 22:35
 */
public class DeduceClient implements Runnable{

    private ConcurrentHashMap<String, String> messageEventTable;

    private ConcurrentHashMap<String, Object[]> shareVarTable;

    private Socket socket;

    private AtomicBoolean stop;

    public DeduceClient(Map<String, String> messageTable, Map<String, Object[]> var, Socket socket, AtomicBoolean stop) {
        this.messageEventTable = new ConcurrentHashMap<>(messageTable);
        this.shareVarTable = new ConcurrentHashMap<>(var);
        this.socket = socket;
        this.stop = stop;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String message = JSON.toJSONString(this.messageEventTable);
                byte[] data = message.getBytes();
                OutputStream outputStream = socket.getOutputStream();
                //outputStream.
            }
        }catch (Exception e) {

        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
