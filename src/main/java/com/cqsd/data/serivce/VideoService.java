package com.cqsd.data.serivce;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqsd.data.entry.Video;
import com.cqsd.data.qo.QueryObject;

public interface VideoService extends IService<Video> {
	Page<Video> selectByPage(QueryObject queryObject);
}
