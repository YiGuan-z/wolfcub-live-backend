package com.cqsd.socket.core.consts;

/**
 * @Desc
 * @Author houry
 * @Date 2021/4/29 11:36
 **/
public interface BarrageCacheKeyConst {

    /**
     * 实时在线人数统计key
     */
    String BARRAGE_ONLINE_POPULATION_KEY = "BARRAGE:ONLINE:POPULATION:";
    /**
     * 历史观看总次数
     */
    String BARRAGE_TOTAL_WATCH_KEY = "BARRAGE:TOTAL:WATCH:";
    /**
     * 历史总弹幕数
     */
    String BARRAGE_TOTAL_MSG_KEY = "BARRAGE:TOTAL:MSG:";
    /**
     * 敏感词汇前缀
     */
    String BARRAGE_MSG_SENSITIVE_KEY = "BARRAGE:MSG:SENSITIVE:KEY:";

}
