package com.cqsd.data.entry;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cqsd.vo.LoginInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 员工管理
 */
@Getter
@Setter
@TableName("sys_employee")
public class Employee {
	@TableId(type = IdType.AUTO)
	
	private Long id;
	private String avatar;
	private String username;
	private String name;
	@JsonIgnore
	private String password;
	private String email;
	private Integer age;
	private Boolean admin;
	@TableField(select = false)
	private Department dept;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@TableField("hireDate")
	private Date hireDate;
	
	public static Employee of(LoginInfo info) {
		final var employee = new Employee();
//		employee.dept = new Department();
		if (info == null) {
			return employee;
		}
		BeanUtils.copyProperties(info, employee);
		return employee;
	}
	
}