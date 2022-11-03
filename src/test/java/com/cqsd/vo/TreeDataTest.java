package com.cqsd.vo;

import com.cqsd.data.entry.Department;
import com.cqsd.data.serivce.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
@SpringBootTest
class TreeDataTest {
	@Autowired
	private DepartmentService service;
	@Test
	void createTreeData(){
		final var departmentList = service.list();
//		System.out.println(departmentList);
		final var cache = departmentList.parallelStream()
				.collect(Collectors.toMap(Department::getId, dept -> dept));
//		System.out.println(cache);
		final var list = departmentList.parallelStream()
				.filter(v -> {
					if (v.getParentId() != null) {
						final var department = cache.get(v.getParentId());
						department.setParent(v);
					}
					return v.getParentId() == null;
				}).collect(Collectors.toList());
		System.out.println(list);
		
		
	}
	
}