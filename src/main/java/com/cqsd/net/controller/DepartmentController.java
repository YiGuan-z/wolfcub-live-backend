package com.cqsd.net.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqsd.data.entry.Department;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.serivce.DepartmentService;
import com.cqsd.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public JsonResult<?> list(QueryObject qo){
        Page<Department> departmentPage = departmentService.selectForList(qo);

        return JsonResult.success(departmentPage);
    }
    @PostMapping("/departments")
    public JsonResult<?> saveOrUpdate(@RequestBody Department department){
        departmentService.saveOrUpdate(department);
        return JsonResult.success(department);
    }
    @DeleteMapping("/departments/{id}")
    public JsonResult<?> delete(@PathVariable("id") Long id){
        if(id==null){
            throw new RuntimeException("id不能为空");
        }
        departmentService.removeById(id);
        return JsonResult.success();
    }
}
