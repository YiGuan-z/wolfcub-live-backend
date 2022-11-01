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
 *
 * @author caseycheng
 */
@Service
public class BulletMsgSensitiveServiceImpl extends ServiceImpl<BulletMsgSensitiveMapper, BulletMsgSensitive> implements BulletMsgSensitiveService {
	private final BulletMsgSensitiveMapper mapper;
	
	public BulletMsgSensitiveServiceImpl(BulletMsgSensitiveMapper mapper) {
		this.mapper = mapper;
	}
	
	public Page<BulletMsgSensitive> query(QueryObject qo) {
		final var page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<BulletMsgSensitive>();
		page.setCurrent(qo.getCurrent()).setSize(qo.getLimit());
		final var selectPage = mapper.selectPage(page, null);
		return new Page<>(qo.getCurrent(), qo.getLimit(), selectPage.getTotal(), selectPage.getRecords());
	}
	
	public List<BulletMsgSensitive> findAll() {
		return mapper.selectList(null);
	}
	
	public boolean save(BulletMsgSensitive obj) {
		obj.setCreateTime(new Date());
		return mapper.insert(obj) > 0;
	}
	
	public boolean updateById(BulletMsgSensitive obj) {
		return mapper.updateById(obj) > 0;
	}
	
	public BulletMsgSensitive findById(Long id) {
		return mapper.selectById(id);
	}
	
	public void deleteById(Long id) {
		mapper.deleteById(id);
	}
}
