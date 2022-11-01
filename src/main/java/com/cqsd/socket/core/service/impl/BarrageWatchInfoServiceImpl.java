package com.cqsd.socket.core.service.impl;

import cn.wolfcode.core.consts.BarrageCacheKeyConst;
import cn.wolfcode.core.service.IBarrageWatchInfoService;
import cn.wolfcode.core.utils.BarrageCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BarrageWatchInfoServiceImpl implements IBarrageWatchInfoService {

    @Override
    public void addOnlineWatchCount(String videoId) {
        BarrageCacheUtils.increment(BarrageCacheKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId);
    }

    @Override
    public void subOnlineWatchCount(String videoId) {
        int onlineWatchCount = Integer.parseInt(BarrageCacheUtils.get(BarrageCacheKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId));
        if(0 >= onlineWatchCount) {
            return;
        }
        BarrageCacheUtils.decrement(BarrageCacheKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId);
    }

    @Override
    public void addTotalWatchCount(String videoId) {
        BarrageCacheUtils.increment(BarrageCacheKeyConst.BARRAGE_TOTAL_WATCH_KEY + videoId);
    }

    @Override
    public String getTotalWatchCount(String videoId) {
        return StringUtils.isBlank(BarrageCacheUtils.get(BarrageCacheKeyConst.BARRAGE_TOTAL_WATCH_KEY + videoId)) ? "0" : BarrageCacheUtils.get(BarrageCacheKeyConst.BARRAGE_TOTAL_WATCH_KEY + videoId);
    }

    @Override
    public String getTotalOnlineWatchCount(String videoId) {
        return StringUtils.isBlank(BarrageCacheUtils.get(BarrageCacheKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId)) ? "0" : BarrageCacheUtils.get(BarrageCacheKeyConst.BARRAGE_ONLINE_POPULATION_KEY + videoId);
    }
}
