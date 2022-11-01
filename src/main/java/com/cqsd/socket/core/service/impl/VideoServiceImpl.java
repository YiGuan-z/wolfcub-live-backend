package com.cqsd.socket.core.service.impl;

import cn.wolfcode.core.service.IVideoService;
import cn.wolfcode.domain.Video;
import cn.wolfcode.mapper.VideoMapper;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements IVideoService {

    private final VideoMapper videoMapper;

    public VideoServiceImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public Video getById(String videoId) {
        return videoMapper.selectByPrimaryKey(videoId);
    }
}
