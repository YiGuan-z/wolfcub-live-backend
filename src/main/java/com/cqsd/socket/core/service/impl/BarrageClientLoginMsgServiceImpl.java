package com.cqsd.socket.core.service.impl;


import com.cqsd.data.entry.Video;
import com.cqsd.data.serivce.BulletMsgService;
import com.cqsd.socket.core.anno.BarrageAnnotation;
import com.cqsd.socket.core.consts.BarrageMsgTypeConst;

import com.cqsd.socket.core.netty.proto.BarrageProto;
import com.cqsd.socket.core.service.IBarrageMsgTypeService;
import com.cqsd.socket.core.service.IBarrageWatchInfoService;
import com.cqsd.socket.core.service.IVideoService;
import com.cqsd.socket.core.utils.BarrageConnectInfoUtils;
import com.cqsd.socket.core.utils.BarrageDateUtils;
import com.google.protobuf.TextFormat;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_LOGIN_REQ)
@Slf4j
public class BarrageClientLoginMsgServiceImpl implements IBarrageMsgTypeService {

    private final BulletMsgService msgService;
    private final IBarrageWatchInfoService watchInfoService;
    private final IVideoService videoService;

    public BarrageClientLoginMsgServiceImpl(BulletMsgService msgService, IBarrageWatchInfoService watchInfoService, IVideoService videoService) {
        this.msgService = msgService;
        this.watchInfoService = watchInfoService;
        this.videoService = videoService;
    }

    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientLoginReq loginInfo = BarrageProto.WebClientLoginReq.parseFrom(barrage.getBytesData());
            log.info("[Req]-[BarrageClientLoginMsgServiceImpl]-[dealWithBarrageMessage]-[params{}]",  TextFormat.printToUnicodeString(loginInfo));
            String videoId = StringUtils.isBlank(loginInfo.getVideoId()) ? "" : loginInfo.getVideoId();
            BarrageConnectInfoUtils.addChannelInfoToBaseMap(videoId, ctx);

            cacheOnlinePopulationAndWatchCountToRedis(videoId);

            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            BarrageProto.WebClientLoginResp.Builder loginResp = BarrageProto.WebClientLoginResp.newBuilder();

            loginResp.setBarrageOnlineCount(Integer.parseInt(watchInfoService.getTotalOnlineWatchCount(videoId)));
            loginResp.setBarrageTotalCount(msgService.getMsgCountByVideoId(videoId));
            loginResp.setBarrageTotalWatchCount(Integer.parseInt(watchInfoService.getTotalWatchCount(videoId)));
            Video video = videoService.getById(videoId);
            loginResp.setVideoTitle(video.getTitle());
            loginResp.setVideoCreateTime(BarrageDateUtils.dateToString(video.getReleaseTime(), BarrageDateUtils.DateType.NORM_DATETIME_PATTERN));

            builder.setBytesData(loginResp.build().toByteString());
            builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_LOGIN_RESP);

            ctx.writeAndFlush(builder);

        } catch (Exception e) {
            log.error("[Exception]-[BarrageClientLoginMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }

    private void cacheOnlinePopulationAndWatchCountToRedis(String videoId) {
        watchInfoService.addOnlineWatchCount(videoId);
        watchInfoService.addTotalWatchCount(videoId);
    }
}
