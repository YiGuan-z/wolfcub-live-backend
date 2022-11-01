package com.cqsd.data.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqsd.data.entry.BulletMsg;

import java.util.List;

/**
 * 弹幕信息
 * @author caseycheng
 */
public interface BulletMsgService extends IService<BulletMsg> {
	int getMsgCountByVideoId(String videoId);
	
	List<BulletMsg> getListByVideoId(String videoId);
	
	List<BulletMsg> getRollingMessages(String videoId, int currentVideoTime);
}
