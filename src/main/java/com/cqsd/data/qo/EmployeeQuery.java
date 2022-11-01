package com.cqsd.data.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeQuery extends QueryObject {

    private Long[] deptIds;
}
