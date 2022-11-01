package com.cqsd.socket.core.service.impl;

import cn.wolfcode.core.bo.BarrageMsgBo;
import cn.wolfcode.core.consts.BarrageMsgTypeConst;
import cn.wolfcode.core.netty.proto.BarrageProto;
import cn.wolfcode.core.service.IBarrageMsgSendToClientService;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BarrageMsgSendToClientServiceImpl implements IBarrageMsgSendToClientService {

    @Override
    public void sendMsg(BarrageMsgBo barrageMsgBo, ChannelHandlerContext context) {
        BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
        BarrageProto.WebClientSendBarrageResp.Builder client = BarrageProto.WebClientSendBarrageResp.newBuilder();
        client.setMsg(barrageMsgBo.getMsg());
        client.setMsgPosition(null == barrageMsgBo.getMsgPosition() ? 0 : barrageMsgBo.getMsgPosition());
        client.setMsgColor(StringUtils.isBlank(barrageMsgBo.getMsgColor()) ? "#fff" : barrageMsgBo.getMsgColor());
        builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_RESP);
        builder.setBytesData(client.build().toByteString());
        context.writeAndFlush(builder);
    }
}
