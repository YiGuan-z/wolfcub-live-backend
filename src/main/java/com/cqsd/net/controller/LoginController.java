package com.cqsd.net.controller;

import com.cqsd.data.serivce.EmployeeService;
import com.cqsd.utils.TokenManager;
import com.cqsd.vo.JsonResult;
import com.cqsd.vo.LoginInfo;
import org.springframework.web.bind.annotation.*;

/**
 * @author caseycheng
 * @date 2022/11/2-02:49
 **/
@RestController
@RequestMapping("/api/v1")
public class LoginController {
	private final EmployeeService service;
	
	public LoginController(EmployeeService service) {
		this.service = service;
	}
	
	@GetMapping("/login")
	public JsonResult<?> userLogin(String username, String password) throws Exception {
		return JsonResult.success(service.login(username, password));
	}
	
	@GetMapping("/logout")
	public JsonResult<?> userLogout(@RequestHeader(TokenManager.TOKEN_NAME) String token) {
		service.logout(token);
		return JsonResult.success();
	}
	
	@GetMapping("/info")
	public JsonResult<LoginInfo> getLoginInfo(@RequestHeader(TokenManager.TOKEN_NAME) String token) {
		return JsonResult.success(TokenManager.getInfo(token));
	}
	@PutMapping("/reset/{id}")
	public JsonResult<?> resetPassword(@PathVariable("id") Long id){
		service.resetPwd(id);
		return JsonResult.success();
	}
}
