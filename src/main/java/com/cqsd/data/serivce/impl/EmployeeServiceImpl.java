package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.Employee;
import com.cqsd.data.mapper.EmployeeMapper;
import com.cqsd.data.serivce.EmployeeService;
import com.cqsd.utils.TokenManager;
import com.cqsd.vo.LoginInfo;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
	private final LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
	
	@Override
	public String login(String username, String password) throws LoginError {
		queryWrapper.clear();
		queryWrapper.eq(Employee::getUsername, username);
		final var list = baseMapper.selectList(queryWrapper);
		final var map = list.parallelStream().collect(Collectors.toMap(Employee::getPassword, emp -> emp));
		final var employee = map.get(password);
		if (Objects.nonNull(employee)) {
			TokenManager.addInfo(TokenManager.createToken()
					, LoginInfo.of(employee));
		}
		throw new LoginError("账号或密码错误");
	}
	
}
