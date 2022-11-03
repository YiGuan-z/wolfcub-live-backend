package com.cqsd.data.serivce;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqsd.data.entry.User;
import com.cqsd.data.qo.UserQuery;

public interface UserService extends IService<User> {
	Page<User> selectForPage(UserQuery query);
}
