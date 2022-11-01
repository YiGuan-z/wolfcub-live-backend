package com.cqsd.socket.core.service;

public interface IBarrageWatchInfoService {

    void addOnlineWatchCount(String videoId);

    void subOnlineWatchCount(String videoId);

    void addTotalWatchCount(String videoId);

    String getTotalWatchCount(String videoId);

    String getTotalOnlineWatchCount(String videoId);
}
