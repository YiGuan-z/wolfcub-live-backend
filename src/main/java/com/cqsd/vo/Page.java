//package com.cqsd.vo;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.Collections;
//import java.util.List;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Page<T> {
//
//    private Integer current;
//    private Integer limit;
//
//    private Long total;
//    private List<T> list;
//
//    public static <T> Page<T> empty(Integer current, Integer limit) {
//        return new Page<>(current, limit, 0L, Collections.emptyList());
//    }
//}
