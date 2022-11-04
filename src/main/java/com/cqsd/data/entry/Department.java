package com.cqsd.data.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 部门管理
 */
@Getter
@Setter
@TableName("sys_department")
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	@TableId(type = IdType.AUTO)
	private Long id;
	private String name;
	private String sn;
	@TableField("parent_id")
	private Long parentId;
	@TableField(select = false)
	private List<Department> children=new ArrayList<>();
	public void addParent(Department son){
		this.children.add(son);
	}
	
	public Department(Object id, Object name, Object sn, Object parentId) {
		this.id = (long) id;
		this.name = (String) name;
		this.sn = (String) sn;
		this.parentId = (Long) parentId;
	}
	
	@Override
	public String toString() {
		return new StringJoiner(", ", Department.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("name='" + name + "'")
				.add("sn='" + sn + "'")
				.add("parentId=" + parentId)
				.add("children=" + children)
				.toString();
	}
}