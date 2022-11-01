package com.cqsd.socket.core.service.impl;


import com.cqsd.data.entry.Video;
import com.cqsd.data.mapper.VideoMapper;
import com.cqsd.socket.core.service.IVideoService;
import org.springframework.stereotype.Service;

@Service
public class BarrageVideoServiceImpl implements IVideoService {

    private final VideoMapper videoMapper;

    public BarrageVideoServiceImpl(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @Override
    public Video getById(String videoId) {
        return videoMapper.selectByPrimaryKey(videoId);
    }
}
