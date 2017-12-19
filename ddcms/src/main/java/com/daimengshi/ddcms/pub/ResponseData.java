package com.daimengshi.ddcms.pub;

import io.jboot.web.controller.JbootController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhoufeng on 2017/11/21.
 * 统一的返回json 格式
 */
public class ResponseData {

    public static final String ERRORS_KEY = "errors";

    private String msg;
    private final int code;
    private final Map<String, Object> data = new HashMap<>();

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }
    public void putDataValue(JbootController mJbootController ,String key, Object value) {
        data.put(key, value);
        mJbootController.renderJson(this);
    }

    private ResponseData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }

    public static ResponseData notFound() {
        return new ResponseData(404, "Not Found");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "unauthorized");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData apiError() {
        return new ResponseData(500, "接口错误");
    }

    public static ResponseData apiError(String msg) {
        return new ResponseData(500, msg);
    }

    public static ResponseData customerError() {
        return new ResponseData(1001, "Customer Error");
    }
}
