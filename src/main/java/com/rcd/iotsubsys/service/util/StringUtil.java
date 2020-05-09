package com.rcd.iotsubsys.service.util;

/**
 * @program: iot-knowledge-sub-system
 * @description: ${description}
 * @author: liyi
 * @create: 2020-05-01 22:00
 */
public class StringUtil {

    public static String splitString(String string, String start, String end) {
        int from = string.indexOf(start) + start.length();
        int to = string.indexOf(end);
        if (from < 0 || to < 0) return null;
        return string.substring(from, to);
    }

}
