package com.cqsd.socket.core.listener;


import com.cqsd.data.entry.BulletMsgSensitive;
import com.cqsd.data.serivce.BulletMsgSensitiveService;
import com.cqsd.socket.core.consts.BarrageCacheKeyConst;
import com.cqsd.socket.core.consts.BarrageVideoConst;
import com.cqsd.socket.core.netty.WebSocketNettyServer;
import com.cqsd.socket.core.utils.BarrageCacheUtils;
import com.cqsd.socket.core.utils.BarrageMsgSensitiveUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @Desc 抽取公共的需要在容器加载完毕之后加载的业务
 **/
@Component
public class BarrageServerListener implements ApplicationListener<ContextRefreshedEvent> {


    private WebSocketNettyServer nettyServer;

    private final BulletMsgSensitiveService bulletMsgSensitiveService;

    public BarrageServerListener(BulletMsgSensitiveService bulletMsgSensitiveService, WebSocketNettyServer nettyServer) {
        this.bulletMsgSensitiveService = bulletMsgSensitiveService;
        this.nettyServer = nettyServer;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        startNetty();
        initSensitiveMsg();
        resetOnlinePopulationNumber();
    }

    private void startNetty() {
        new Thread(() -> nettyServer.startNettyServer()).start();
    }

    private void initSensitiveMsg() {
        List<BulletMsgSensitive> msgSensitives = bulletMsgSensitiveService.findAll();
        BarrageMsgSensitiveUtils.setSensitiveWords(msgSensitives);
        msgSensitives.forEach(msgSensitive -> BarrageCacheUtils.hashPut(BarrageCacheKeyConst.BARRAGE_MSG_SENSITIVE_KEY, msgSensitive.getSensitiveMsg(), msgSensitive.getShowMsg()));
    }

    private void resetOnlinePopulationNumber() {
        BarrageCacheUtils.set(BarrageCacheKeyConst.BARRAGE_ONLINE_POPULATION_KEY + BarrageVideoConst.VIDEO_ID, "0");
    }

}
