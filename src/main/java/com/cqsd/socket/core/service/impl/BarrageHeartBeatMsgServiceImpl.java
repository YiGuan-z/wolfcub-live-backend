package com.cqsd.socket.core.service.impl;

import cn.wolfcode.core.consts.BarrageMsgTypeConst;
import cn.wolfcode.core.netty.proto.BarrageProto;
import cn.wolfcode.core.service.IBarrageMsgTypeService;
import cn.wolfcode.core.service.IBarrageWatchInfoService;
import cn.wolfcode.service.IBulletMsgService;
import com.cqsd.socket.core.anno.BarrageAnnotation;
import com.cqsd.socket.core.consts.BarrageMsgTypeConst;
import com.cqsd.socket.core.service.IBarrageMsgTypeService;
import com.google.protobuf.TextFormat;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @Desc 心跳包信息
 **/
@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_HEART_BEAT_REQ)
@Slf4j
public class BarrageHeartBeatMsgServiceImpl implements IBarrageMsgTypeService {

    private final IBulletMsgService msgService;
    private final IBarrageWatchInfoService watchInfoService;

    public BarrageHeartBeatMsgServiceImpl(IBarrageWatchInfoService watchInfoService, IBulletMsgService msgService) {
        this.watchInfoService = watchInfoService;
        this.msgService = msgService;
    }


    /**
     * 处理心跳逻辑
     *
     * @param barrage {@link BarrageProto}
     * @param ctx     通道上下文信息
     */
    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientHeartBeatReq heartBeatReq = BarrageProto.WebClientHeartBeatReq.parseFrom(barrage.getBytesData());
            log.info("[Req]-[BarrageHeartBeatMsgServiceImpl]-[dealWithBarrageMessage]-[接收到心跳]-[ctx:{}]-[params:{}]", ctx.channel().toString(), TextFormat.printToUnicodeString(heartBeatReq));

            String videoId = StringUtils.isBlank(heartBeatReq.getVideoId()) ? "" : heartBeatReq.getVideoId();

            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            BarrageProto.WebClientHeartBeatResp.Builder resp = BarrageProto.WebClientHeartBeatResp.newBuilder();

            resp.setBarrageOnlineCount(Integer.parseInt(watchInfoService.getTotalOnlineWatchCount(videoId)));
            resp.setBarrageTotalCount(msgService.getMsgCountByVideoId(videoId));
            resp.setBarrageTotalWatchCount(Integer.parseInt(watchInfoService.getTotalWatchCount(videoId)));

            builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_HEART_BEAT_RESP);
            builder.setBytesData(resp.build().toByteString());
            ctx.writeAndFlush(builder);
        } catch (Exception e) {
            log.error("[Exception]-[BarrageHeartBeatMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
