package com.fzh.com.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {
    public ResponseUtil(){}
    private static Map map = new HashMap();
    private final static Integer successCode = 20000;//成功
    private final static Integer failCode = 50000;//失败

    private Integer code;
    private String message;
    private Object data;


    /**
     * 成功时返回
     *
     * @param dataObj Object 成功返回的数据对象
     * @return json字符串
     */
    public static String success(Object dataObj) {
        map.put("code", successCode);
        map.put("message", "成功");
        map.put("data", dataObj);
        return jsonString(map);
    }

    /**
     * 失败时返回
     *
     * @param message String 失败返回的提示信息
     * @return json字符串
     */
    public static String error(String message) {
        map.put("code", failCode);
        map.put("message", message);
        map.put("data", null);
        return jsonString(map);
    }

    /**
     * 自定义返回数据
     *
     * @param code    Integer 返回码
     * @param message String 返回的提示信息
     * @param dataObj Object 返回的提示信息
     * @return json字符串
     */
    public static String customResult(Integer code, String message, Object dataObj) {
        map.put("code", code);
        map.put("message", message);
        map.put("data", dataObj);
        return jsonString(map);
    }

    /**
     * 返回layui分页表格
     */
    public static String layuiTablePage(Integer code, String message, Object dataObj, Integer count) {
        map.put("code", code);
        map.put("msg", message);
        map.put("data", dataObj);
        map.put("count", count);
        return jsonString(map);
    }

    private static String jsonString(Object object) {
        return JSON.toJSONString(object, SerializerFeature.WriteMapNullValue, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteDateUseDateFormat);
    }


    public ResponseUtil(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
