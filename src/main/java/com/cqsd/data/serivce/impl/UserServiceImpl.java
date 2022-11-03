package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.User;
import com.cqsd.data.mapper.UserMapper;
import com.cqsd.data.qo.UserQuery;
import com.cqsd.data.serivce.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	private final LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
	
	@Override
	public Page<User> selectForPage(UserQuery query) {
		queryWrapper.clear();
		if (StringUtils.hasLength(query.getKeyword())) {
			queryWrapper.like(User::getUsername, query.getKeyword()).or(qw -> qw.like(User::getNickname, query.getKeyword()));
		}
		if (Objects.nonNull(query.getStatus())) {
			queryWrapper.eq(User::getStatus, query.getStatus());
		}
		final Page<User> page = Page.of(query.getCurrent(), query.getLimit());
		return baseMapper.selectPage(page, queryWrapper);
	}
}
