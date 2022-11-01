package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.Department;
import com.cqsd.data.mapper.DepartMentMapper;
import com.cqsd.data.serivce.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartMentMapper, Department> implements DepartmentService {
}
