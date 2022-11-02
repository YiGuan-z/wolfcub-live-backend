package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.BulletMsgSensitive;
import com.cqsd.data.mapper.BulletMsgSensitiveMapper;
import com.cqsd.data.serivce.BulletMsgSensitiveService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 弹幕敏感词管理
 *
 * @author caseycheng
 */
@Service
public class BulletMsgSensitiveServiceImpl extends ServiceImpl<BulletMsgSensitiveMapper, BulletMsgSensitive> implements BulletMsgSensitiveService {

	
	public List<BulletMsgSensitive> findAll() {
		return baseMapper.selectList(null);
	}
	
	public boolean save(BulletMsgSensitive obj) {
		obj.setCreateTime(new Date());
		return baseMapper.insert(obj) > 0;
	}

//	public boolean updateById(BulletMsgSensitive obj) {
//		return mapper.updateById(obj) > 0;
//	}

//	public BulletMsgSensitive findById(Long id) {
//		return mapper.selectById(id);
//	}
//
//	public void deleteById(Long id) {
//		mapper.deleteById(id);
//	}
}
