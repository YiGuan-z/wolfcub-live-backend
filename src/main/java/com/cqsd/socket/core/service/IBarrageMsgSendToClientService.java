package com.cqsd.socket.core.service;

import com.cqsd.socket.core.bo.BarrageMsgBo;
import io.netty.channel.ChannelHandlerContext;

public interface IBarrageMsgSendToClientService {
    void sendMsg(BarrageMsgBo barrageMsgBo, ChannelHandlerContext context);
}
