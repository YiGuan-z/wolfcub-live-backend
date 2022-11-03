package com.cqsd.data.serivce;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqsd.data.entry.Department;
import com.cqsd.data.qo.QueryObject;

/**
 * 部门管理
 * @author caseycheng
 */
public interface DepartmentService extends IService<Department> {
    Page<Department> selectForList(QueryObject qo);
}
