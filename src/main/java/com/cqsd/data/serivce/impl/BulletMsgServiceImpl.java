package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.BulletMsg;
import com.cqsd.data.mapper.BulletMsgMapper;
import com.cqsd.data.serivce.BulletMsgService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 弹幕消息查询
 *
 * @author caseycheng
 */
// FIXME: 2022/11/2
@Service
public class BulletMsgServiceImpl extends ServiceImpl<BulletMsgMapper, BulletMsg> implements BulletMsgService {
	@Override
	public int getMsgCountByVideoId(String videoId) {
		//sql
		//    select count(*)
		//        from t_bullet_msg
		//        where video_id = #{videoId} and status = 0
		final var wrapper = new QueryWrapper<BulletMsg>();
		wrapper.eq("video_id", videoId).and(wr -> wr.eq("status", 0));
		return Math.toIntExact(baseMapper.selectCount(wrapper));
	}
	
	@Override
	public List<BulletMsg> getListByVideoId(String videoId) {
		// select * from t_bullet_msg
		//        where video_id = #{videoId}
		final var wrapper = new QueryWrapper<BulletMsg>();
		wrapper.eq("video_id", videoId);
		return baseMapper.selectList(wrapper);
	}
	
	@Override
	public List<BulletMsg> getRollingMessages(String videoId, int currentVideoTime) {
		// select * from t_bullet_msg where video_id = #{videoId} and video_time = #{currentVideoTime}
		final var wrapper = new QueryWrapper<BulletMsg>();
		wrapper.eq("video_id", videoId).eq("video_time", currentVideoTime);
		return baseMapper.selectList(wrapper);
	}
}
