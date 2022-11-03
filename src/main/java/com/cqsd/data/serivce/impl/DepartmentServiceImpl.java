package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.Department;
import com.cqsd.data.entry.Employee;
import com.cqsd.data.mapper.DepartmentMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.serivce.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    private final LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
    private final LambdaUpdateWrapper<Department> updateWrapper=new LambdaUpdateWrapper<>();
    @Override
    public Page<Department> selectForList(QueryObject qo) {
        queryWrapper.clear();
        if (qo.getKeyword() != null) {
            queryWrapper.like(Department::getName, qo.getKeyword());
        }
        return baseMapper.selectPage(new Page<>(qo.getCurrent(), qo.getLimit()), queryWrapper);
    }
}
