package com.rcd.iotsubsys.dto.response.base;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-03-04 13:32
 */
public enum ResponseCode {

    /**
     * 成功返回值
     * */
    SUCCESS(0, "success"),
    IOT_DIRECTORY_SAVE_OR_UPDATE_ERROR(1, "增加或更新目录结点信息失败"),
    PARAM_ILLAGLE_OR_NULL(2, "缺少参数或参数异常"),
    DIRECTORY_NODE_HAS_CHILD(3, "目录结点有子节点，禁止删除"),
    DIRECTORY_NODE_NOT_EXIST(4, "目录节点不存在"),
    DIRECTORY_WITHOUT_OWNER(5, "缺少owner参数， 无效的查询"),
    KNOWLEDGE_MODEL_NAME_EMPTY(6, "知识模型名称为空"),
    KNOWLEDGE_MODEL_DUPLIC(7,"当前知识文件已经存在"),

    EVENT_METAEVENT_EXIST(8, "当前原子事件已经存在"),
    EVENT_COMPLEXEVENT_EXIST(9, "当前复杂事件已经存在");

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

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
    }}
