package com.cqsd.socket.core.service.impl;

import cn.wolfcode.core.anno.BarrageAnnotation;
import cn.wolfcode.core.consts.BarrageMsgTypeConst;
import cn.wolfcode.core.netty.proto.BarrageProto;
import cn.wolfcode.core.service.IBarrageMsgTypeService;
import cn.wolfcode.core.service.IBarrageWatchInfoService;
import cn.wolfcode.core.utils.BarrageConnectInfoUtils;
import com.google.protobuf.TextFormat;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_LOGOUT_REQ)
@Slf4j
public class BarrageClientLogoutMsgServiceImpl implements IBarrageMsgTypeService {

    private final IBarrageWatchInfoService watchInfoService;

    public BarrageClientLogoutMsgServiceImpl(IBarrageWatchInfoService watchInfoService) {
        this.watchInfoService = watchInfoService;
    }

    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientLogoutReq logoutInfo = BarrageProto.WebClientLogoutReq.parseFrom(barrage.getBytesData());
            log.info("[Req]-[BarrageClientLogoutMsgServiceImpl]-[dealWithBarrageMessage]-[params{}]",  TextFormat.printToUnicodeString(logoutInfo));
            String videoId = StringUtils.isBlank(logoutInfo.getVideoId()) ? "" : logoutInfo.getVideoId();
            BarrageConnectInfoUtils.removeChannelInfoFromBaseMap(videoId, ctx);
            watchInfoService.subOnlineWatchCount(videoId);
        } catch (Exception e) {
            log.error("[Exception]-[BarrageClientLogoutMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
