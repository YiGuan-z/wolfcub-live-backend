package com.cqsd.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TreeData {

    private Long id;
    private String label;
    private List<TreeData> children = new ArrayList<>();

    public TreeData(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
