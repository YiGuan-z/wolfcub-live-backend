package com.cqsd.data.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuery extends QueryObject {

    private Integer status = -1;
    private Integer deleted = 0;

    public void setDeleted(Integer deleted) {
        // 如果没有设置 deleted，默认查询未被删除的
        this.deleted = deleted == null ? 0 : deleted;
    }
}
