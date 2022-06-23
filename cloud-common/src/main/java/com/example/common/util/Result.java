package com.example.common.util;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;

/**
 * 返回结果封装
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Result extends HashMap<String, Object> {
    public static final String CODE_TAG = "code";

    public static final Integer OK_CODE = 200;



    public Result(int code, String msg) {
        put("code", code);
        put("msg", msg);
    }

    public static Result OK() {
        return new Result(200, "success");
    }

    public static Result OK(Object data) {
        return OK().put(data);
    }

    public Result put(Object data) {
        this.put("data", data);
        return this;
    }

    public static Result of(boolean bool) {
        return bool ? Result.OK() : Result.Error();
    }

    public static Result of(Integer num) {
        return num > 0 ? Result.OK() : Result.Error();
    }

    public static Result Error() {
        return new Result(500, "error");
    }

    public boolean isOK() {
        return OK_CODE.equals(get(CODE_TAG));
    }

    public Integer getCode() {
        return (Integer) get(CODE_TAG);
    }
}
