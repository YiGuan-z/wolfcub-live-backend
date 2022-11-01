package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.BulletMsg;
import com.cqsd.data.mapper.BulletMapper;
import com.cqsd.data.serivce.BulletMegService;
import org.springframework.stereotype.Service;

/**
 * 弹幕举报管理
 * @author caseycheng
 */
@Service
public class BulletMegServiceImpl extends ServiceImpl<BulletMapper, BulletMsg> implements BulletMegService {
}
