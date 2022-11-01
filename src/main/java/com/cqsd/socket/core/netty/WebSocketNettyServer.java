package com.cqsd.socket.core.netty;


import com.cqsd.socket.core.listener.BarrageNettyServerListener;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @Desc netty 服务
 **/
@Slf4j
public class WebSocketNettyServer {

    private Integer serverPort;

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * socket 连接处理循环组
     */
    EventLoopGroup boss = new NioEventLoopGroup(1, new WebSocketNettyServerThreadFactory("netty-screen-boss"));
    /**
     * socket 业务处理循环组
     */
    EventLoopGroup worker = new NioEventLoopGroup(1, new WebSocketNettyServerThreadFactory("netty-screen-worker"));


    /**
     * 关闭Netty服务
     */
    public void shutDownNettyServer() {
        try {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            log.info("[WebSocketNettyServer]-[shutDownNettyServer]-[shutDown]");
        } catch (Exception e) {
            log.error("[WebSocketNettyServer]-[shutDownNettyServer]-[Exception]");
        }

    }

    /**
     * 开启Netty服务
     */
    public void startNettyServer() {
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new WebSocketNettyServerInitializer());
            ChannelFuture channelFuture = server.bind(serverPort).addListener(new BarrageNettyServerListener(serverPort)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("[WebSocketNettyServer]-[startNettyServer]-[Exception]", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            log.info("[WebSocketNettyServer]-[startNettyServer]-[shutdownGracefully]");
        }

    }

}
