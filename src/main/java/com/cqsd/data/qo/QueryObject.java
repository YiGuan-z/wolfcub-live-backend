package com.cqsd.data.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {

    private Integer current = 1;
    private Integer limit = 10;
    private String keyword;

    private Integer getStart() {
        return (current - 1) * limit;
    }
}
