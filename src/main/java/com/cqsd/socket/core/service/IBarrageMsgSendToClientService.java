package com.cqsd.socket.core.service;

import cn.wolfcode.core.bo.BarrageMsgBo;
import io.netty.channel.ChannelHandlerContext;

public interface IBarrageMsgSendToClientService {
    void sendMsg(BarrageMsgBo barrageMsgBo, ChannelHandlerContext context);
}
