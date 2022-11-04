package com.cqsd.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * HashMap版本的JsonResult，
 * 便携构造一个返回对象
 * 在这里我们可以随意往里面入参
 * @author caseycheng
 * @date 2022/11/4-18:27
 **/
public class JsonResultMap {
	public static final Integer DEFALT_SUCCESS_CODE = 200;
	public static final Integer DEFALT_FAILED_CODE = 401;
	
	public static final String DEFALT_SUCCESS_MSG = "success";
	public static final String DEFALT_FAILED_MSG = "failed";
	
	public static <T> Map<String, Object> of(Integer code, String msg, T data) {
		final var ret = new HashMap<String, Object>();
		ret.put("code", code);
		ret.put("msg", msg);
		if (Objects.nonNull(data)) {
			ret.put("data", data);
		}
		return ret;
	}
	
	public static <T> Map<String, Object> success(String msg, T data) {
		return of(DEFALT_SUCCESS_CODE, msg, data);
	}
	
	public static <T> Map<String, Object> success(T data) {
		return success(DEFALT_SUCCESS_MSG, data);
	}
	
	public static Map<String, Object> success() {
		return success(null);
	}
	
	public static Map<String, Object> failed(Integer code, String msg) {
		return of(code, msg, null);
	}
	
	public static Map<String, Object> failed(String msg) {
		return failed(DEFALT_FAILED_CODE, msg);
	}
	
	public static Map<String, Object> failed() {
		return failed(DEFALT_FAILED_MSG);
	}
	
}
