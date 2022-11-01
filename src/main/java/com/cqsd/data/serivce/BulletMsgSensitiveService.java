package com.cqsd.data.serivce;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqsd.data.entry.BulletMsgSensitive;

import java.util.List;

/**
 * 弹幕敏感词管理
 * @author caseycheng
 */
public interface BulletMsgSensitiveService extends IService<BulletMsgSensitive> {
	List<BulletMsgSensitive> findAll();
}
