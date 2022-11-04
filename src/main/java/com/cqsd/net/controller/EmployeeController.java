package com.cqsd.net.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqsd.data.entry.Employee;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.serivce.EmployeeService;
import com.cqsd.utils.TokenManager;
import com.cqsd.vo.JsonResult;
import org.springframework.util.StringUtils;
import com.cqsd.vo.LoginInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getInfo")
    public JsonResult<?> getInfo(@RequestHeader(value = TokenManager.TOKEN_NAME, required = false) String token) {
        if (!StringUtils.hasText(token)) {
            throw new RuntimeException("token 不能为空");
        }
        LoginInfo info = TokenManager.getInfo(token);
        return JsonResult.success(info);
    }

    @PostMapping("/updateInfo")
    public JsonResult<?> updateByInfo(@RequestBody LoginInfo loginInfo, @RequestHeader(TokenManager.TOKEN_NAME) String token) {
        LoginInfo info = TokenManager.getInfo(token);
        loginInfo.setId(info.getId());
        BeanUtils.copyProperties(loginInfo, info);
        Employee employee = Employee.of(info);
        employeeService.updateById(employee);
        //更新用户在缓存中的数据
        return JsonResult.success(loginInfo);
    }

    @GetMapping("/employees")
    public JsonResult<?> list(QueryObject qo) {
        Page<Employee> employeePage = employeeService.selectForList(qo);

        return JsonResult.success(employeePage);
    }

    @PostMapping("/employees")
    public JsonResult<?> saveOrUpdate(@RequestBody Employee employee) {
        employeeService.saveOrUpdate(employee);
        return JsonResult.success(employee);
    }

    @DeleteMapping("/employees/{id}")
    public JsonResult<?> delete(@PathVariable("id") Long id) {
        if (id == null) {
            throw new RuntimeException("id不能为空");
        }
        employeeService.removeById(id);
        return JsonResult.success();
    }
}
