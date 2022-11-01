package com.cqsd.socket.core.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desc NettyServer 绑定端口的监听事件
 **/
@Slf4j
public class BarrageNettyServerListener implements ChannelFutureListener {

    /**
     * 端口号
     */
    private final Integer port;

    public BarrageNettyServerListener(Integer port) {
        this.port = port;
    }

    /**
     * 端口绑定之后发生的操作
     *
     * @param future
     * @throws Exception
     */
    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (future.isSuccess()) {
            log.info("[NettyServerListener]-[NettyServer bind port {} success]", port);
        } else {
            log.info("[NettyServerListener]-[NettyServer bind port {} fail]", port);
        }
    }
}
