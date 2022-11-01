package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.BulletMsgSensitive;
import com.cqsd.data.mapper.BulletMsgSensitiveMapper;
import com.cqsd.data.qo.QueryObject;
import com.cqsd.data.serivce.BulletMsgSensitiveService;
import com.cqsd.vo.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 弹幕敏感词管理
 * @author caseycheng
 */
@Service
public class BulletMsgSensitiveServiceImpl extends ServiceImpl<BulletMsgSensitiveMapper,BulletMsgSensitive> implements BulletMsgSensitiveService  {
	private final BulletMsgSensitiveMapper mapper;
	
	public BulletMsgSensitiveServiceImpl(BulletMsgSensitiveMapper mapper) {
		this.mapper = mapper;
	}
	
	public Page<BulletMsgSensitive> query(QueryObject qo) {
		Long count = mapper.selectForCount(qo);
		if (count == null) {
			return Page.empty(qo.getCurrent(), qo.getLimit());
		}
		List<BulletMsgSensitive> bulletMsgSensitives = mapper.selectForList(qo);
		return new Page<>(qo.getCurrent(), qo.getLimit(), count, bulletMsgSensitives);
	}
	
	public List<BulletMsgSensitive> findAll() {
		return mapper.selectAll();
	}
	
	public void save(BulletMsgSensitive obj) {
		obj.setCreateTime(new Date());
		mapper.insert(obj);
	}
	
	public void updateById(BulletMsgSensitive obj) {
		mapper.updateByPrimaryKey(obj);
	}
	
	public BulletMsgSensitive findById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public void deleteById(Long id) {
		mapper.deleteByPrimaryKey(id);
	}
}
