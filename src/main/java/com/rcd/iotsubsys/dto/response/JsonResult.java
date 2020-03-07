package com.rcd.iotsubsys.dto.response;

import com.rcd.iotsubsys.dto.response.base.ResponseCode;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-04 13:30
 */
public class JsonResult<T> {

    private int code;
    private String msg;
    private T data;

    public JsonResult() {
        this.code = 0;
        this.msg = "success";
    }

    public JsonResult(ResponseCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public JsonResult(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            ", data=" + data +
            '}';
    }

    public boolean failure() {
        return ResponseCode.SUCCESS.getCode() != this.code;
    }
}
