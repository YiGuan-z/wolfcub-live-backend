package com.cqsd.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.cqsd.utils.WebConstants.*;

/**
 * return Json
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<T> {
	
	private Integer code;
	private String msg;
	private T data;
	
	private static <T> JsonResult<T> of(Integer code, String msg, T data) {
		return new JsonResult<>(code, msg, data);
	}
	
	public static <T> JsonResult<T> success(Integer code, String msg, T data) {
		return of(code, msg, data);
	}
	
	public static <T> JsonResult<T> success(String msg, T data) {
		return of(DEFAULT_SUCCESS_CODE, msg, data);
	}
	
	public static <T> JsonResult<T> success(T data) {
		return success(DEFAULT_SUCCESS_MSG, data);
	}
	
	public static <T> JsonResult<T> success() {
		return success(null);
	}
	
	public static <T> JsonResult<T> failed(Integer code, String msg, T data) {
		return of(code, msg, data);
	}
	
	public static <T> JsonResult<T> faied(Integer code, String msg) {
		return failed(code, msg, null);
	}
	
	public static <T> JsonResult<T> failed(String msg, T data) {
		return of(DEFAULT_FAILED_CODE, msg, data);
	}
	
	public static <T> JsonResult<T> failed(T data) {
		return failed(DEFAULT_FAILED_MSG, data);
	}
	
	public static <T> JsonResult<T> failed() {
		return failed(null);
	}
	
}

