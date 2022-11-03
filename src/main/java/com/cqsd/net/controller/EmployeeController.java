package com.cqsd.net.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqsd.data.entry.Employee;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.serivce.EmployeeService;
import com.cqsd.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public JsonResult<?> list(QueryObject qo){
        Page<Employee> employeePage = employeeService.selectForList(qo);

        return JsonResult.success(employeePage);
    }
    @PostMapping("/employees")
    public JsonResult<?> saveOrUpdate(@RequestBody Employee employee){
        employeeService.saveOrUpdate(employee);
        return JsonResult.success(employee);
    }
    @DeleteMapping("/employees/{id}")
    public JsonResult<?> delete(@PathVariable("id") Long id){
        if(id==null){
            throw new RuntimeException("id不能为空");
        }
        employeeService.removeById(id);
        return JsonResult.success();
    }
}
