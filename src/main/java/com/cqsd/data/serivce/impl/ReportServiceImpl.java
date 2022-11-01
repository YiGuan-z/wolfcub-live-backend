package com.cqsd.data.serivce.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqsd.data.entry.Report;
import com.cqsd.data.mapper.ReportMapper;
import com.cqsd.data.serivce.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {
}
