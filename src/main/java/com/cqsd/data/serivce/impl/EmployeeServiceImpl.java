package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.Employee;
import com.cqsd.data.mapper.EmployeeMapper;
import com.cqsd.data.serivce.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {}
