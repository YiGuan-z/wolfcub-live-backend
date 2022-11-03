package com.cqsd.net.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqsd.data.entry.User;
import com.cqsd.data.qo.UserQuery;
import com.cqsd.data.serivce.UserService;
import com.cqsd.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author caseycheng
 * @date 2022/11/3-20:21
 **/
@RestController
@RequestMapping("/api/v1")
public class UserController {
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping("/user")
	public JsonResult<?> getForList(UserQuery query) {
		final var page = service.selectForPage(query);
		return JsonResult.success(page);
	}
	
	@PostMapping("/user")
	public JsonResult<?> saveOrUpdate(@RequestBody User user) {
		service.saveOrUpdate(user);
		return JsonResult.success(user);
	}
	
	@DeleteMapping("/user")
	public JsonResult<?> deleteByIds(ArrayList<Long> ids) {
		service.removeByIds(ids);
		return JsonResult.success(ids);
	}
	
}
