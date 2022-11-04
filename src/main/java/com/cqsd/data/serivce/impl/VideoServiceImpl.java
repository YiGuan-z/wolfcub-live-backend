package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.Video;
import com.cqsd.data.mapper.VideoMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.serivce.VideoService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
	private final LambdaQueryWrapper<Video> queryWrapper= Wrappers.lambdaQuery();
	@Override
	public Page<Video> selectByPage(QueryObject queryObject) {
		queryWrapper.clear();
		if (Objects.nonNull(queryObject.getKeyword())){
			queryWrapper.like(Video::getTitle,queryObject.getKeyword()).or(wapper->wapper.like(Video::getSubTitle,queryObject.getKeyword()));
		}
		
		return baseMapper.selectPage(Page.of(queryObject.getCurrent(), queryObject.getLimit()),queryWrapper);
	}
}
