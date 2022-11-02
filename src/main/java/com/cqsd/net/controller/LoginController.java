package com.cqsd.net.controller;

import com.cqsd.data.serivce.EmployeeService;
import com.cqsd.vo.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
