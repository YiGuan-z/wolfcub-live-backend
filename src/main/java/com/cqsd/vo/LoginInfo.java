package com.cqsd.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * 专门用于封装登录后返回用户信息的对象
 */
@Getter
@Setter
public class LoginInfo {

    private Long id;
    private String name;
    private String username;
    private String avatar;
    public static LoginInfo of(Object obj){
        final var info = new LoginInfo();
        BeanUtils.copyProperties(obj,info);
        return info;
    }
}
