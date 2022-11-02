package com.cqsd.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.cqsd.utils.WebConstants.*;
import static com.cqsd.utils.WebConstants.DEFAULT_FAILED_MSG;

/**
 * @author caseycheng
 * @date 2022/11/2-19:00
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ɹɪˈzʌlts<T> {
	private Integer code;
	private String msg;
	private T data;
	
	private static <T> ɹɪˈzʌlts<T> of(Integer code, String msg, T data) {
		return new ɹɪˈzʌlts<>(code, msg, data);
	}
	
	public static <T> ɹɪˈzʌlts<T> success(Integer code, String msg, T data) {
		return of(code, msg, data);
	}
	
	public static <T> ɹɪˈzʌlts<T> success(String msg, T data) {
		return of(DEFAULT_SUCCESS_CODE, msg, data);
	}
	
	public static <T> ɹɪˈzʌlts<T> success(T data) {
		return success(DEFAULT_SUCCESS_MSG, data);
	}
	
	public static <T> ɹɪˈzʌlts<T> success() {
		return success(null);
	}
	
	public static <T> ɹɪˈzʌlts<T> failed(Integer code, String msg, T data) {
		return of(code, msg, data);
	}
	
	public static <T> ɹɪˈzʌlts<T> faied(Integer code, String msg) {
		return failed(code, msg, null);
	}
	
	public static <T> ɹɪˈzʌlts<T> failed(String msg, T data) {
		return of(DEFAULT_FAILED_CODE, msg, data);
	}
	
	public static <T> ɹɪˈzʌlts<T> failed(T data) {
		return failed(DEFAULT_FAILED_MSG, data);
	}
	
	public static <T> ɹɪˈzʌlts<T> failed() {
		return failed(null);
	}
}
