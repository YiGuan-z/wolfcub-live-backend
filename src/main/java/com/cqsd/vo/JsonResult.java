package com.cqsd.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> {

    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final String DEFAULT_SUCCESS_MSG = "success";
    public static final Integer DEFAULT_FAILED_CODE = 500;
    public static final String DEFAULT_FAILED_MSG = "服务器繁忙";

    private Integer code;
    private String msg;
    private T data;

    public static <T> JsonResult<T> success() {
        return success(null);
    }

    public static <T> JsonResult<T> success(T data) {
        return success(DEFAULT_SUCCESS_MSG, data);
    }

    public static <T> JsonResult<T> success(String msg, T data) {
        return new JsonResult<>(DEFAULT_SUCCESS_CODE, msg, data);
    }

    public static <T> JsonResult<T> failed() {
        return failed(DEFAULT_FAILED_MSG);
    }

    public static <T> JsonResult<T> failed(String msg) {
        return failed(DEFAULT_FAILED_CODE, msg);
    }

    public static <T> JsonResult<T> failed(Integer code, String msg) {
        return new JsonResult<>(code, msg, null);
    }
    
    @Override
    public String toString() {
        return new StringJoiner(", ", JsonResult.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("msg='" + msg + "'")
                .add("data=" + data)
                .toString();
    }
}

