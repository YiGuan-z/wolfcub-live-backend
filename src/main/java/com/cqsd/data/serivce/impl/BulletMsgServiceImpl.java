package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.BulletMsg;
import com.cqsd.data.mapper.BulletMsgMapper;
import com.cqsd.data.serivce.BulletMsgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 弹幕举报管理
 * @author caseycheng
 * TODO 待实现
 */
@Service
public class BulletMsgServiceImpl extends ServiceImpl<BulletMsgMapper, BulletMsg> implements BulletMsgService {
	@Override
	public int getMsgCountByVideoId(String videoId) {
		return 0;
	}
	
	@Override
	public List<BulletMsg> getListByVideoId(String videoId) {
		return null;
	}
	
	@Override
	public List<BulletMsg> getRollingMessages(String videoId, int currentVideoTime) {
		return null;
	}
}
