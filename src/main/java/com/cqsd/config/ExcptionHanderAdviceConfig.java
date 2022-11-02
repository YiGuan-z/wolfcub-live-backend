package com.cqsd.config;

import com.cqsd.data.serivce.EmployeeService;
import com.cqsd.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author caseycheng
 * @date 2022/11/2-15:49
 **/
@Slf4j
@RestControllerAdvice
public class ExcptionHanderAdviceConfig {
	@ExceptionHandler(Throwable.class)
	public JsonResult<?> defaultExceptionHandler(Throwable e) {
		if (e instanceof EmployeeService.LoginError) {
			return JsonResult.faied(401, e.getMessage());
		} else if (e instanceof RuntimeException) {
			log.error("{}[未侦测的异常]{}", System.currentTimeMillis(), e.getMessage());
			return JsonResult.faied(501, "服务器出现异常，请参阅服务器日志");
		} else {
			log.error("{}[发现异常]{}", System.currentTimeMillis(), e.getMessage());
			return JsonResult.faied(500, "服务器出现异常，请查看服务器日志");
		}
		
	}
}
