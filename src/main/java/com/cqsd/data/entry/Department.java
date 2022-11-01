package com.cqsd.data.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 部门管理
 */
@Getter
@Setter
@TableName("sys_department")
public class Department {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String sn;
    private Department parent;
}