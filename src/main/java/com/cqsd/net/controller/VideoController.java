package com.cqsd.net.controller;

import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.serivce.VideoService;
import com.cqsd.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caseycheng
 * @date 2022/11/3-21:51
 **/
@RestController
@RequestMapping("/api/v1")
public class VideoController {
	private final VideoService service;
	
	public VideoController(VideoService service) {
		this.service = service;
	}
	
	@GetMapping("/video")
	public JsonResult<?> getList(QueryObject queryObject) {
		return JsonResult.success(service.selectByPage(queryObject));
	}
}
