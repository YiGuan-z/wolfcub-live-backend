package com.cqsd.data.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {
    @TableId(type = IdType.AUTO)

    private Long id;
    private String name;
    private String sn;
    private Department parent;
}