package com.cqsd.socket.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.wolfcode.core.anno.BarrageAnnotation;
import cn.wolfcode.core.consts.BarrageMsgTypeConst;
import cn.wolfcode.core.netty.proto.BarrageProto;
import cn.wolfcode.core.service.IBarrageMsgTypeService;
import cn.wolfcode.core.utils.BarrageDateUtils;
import cn.wolfcode.domain.BulletMsg;
import cn.wolfcode.service.IBulletMsgService;
import com.google.protobuf.TextFormat;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_HISTORY_REQ)
@Slf4j
public class BarrageHistoryListServiceImpl implements IBarrageMsgTypeService {

    private final IBulletMsgService barrageMsgService;

    public BarrageHistoryListServiceImpl(IBulletMsgService barrageMsgService) {
        this.barrageMsgService = barrageMsgService;
    }


    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientBarrageHistoryListReq barrageHistory = BarrageProto.WebClientBarrageHistoryListReq.parseFrom(barrage.getBytesData());
            log.info("[Req]-[BarrageHistoryListServiceImpl]-[dealWithBarrageMessage]-[params{}]", TextFormat.printToUnicodeString(barrageHistory));
            String videoId = barrageHistory.getVideoId();
            List<BulletMsg> barrageMsgList = barrageMsgService.getListByVideoId(videoId);

            List<BarrageProto.BarrageHistoryMessage> msgList = new ArrayList<>();
            barrageMsgList.forEach(v -> {
                BarrageProto.BarrageHistoryMessage.Builder message = BarrageProto.BarrageHistoryMessage.newBuilder();
                message.setMsg(v.getColor());
                message.setCreateTime(BarrageDateUtils.dateToString(v.getReleaseTime(), BarrageDateUtils.DateType.PURE_DATE_MD_HM_PATTERN));
                message.setMsgColor(v.getColor());
                message.setSendTime(BarrageDateUtils.secondToNormTime(ObjectUtil.defaultIfNull(v.getVideoTime(), 0)));
                message.setMsg(v.getContent());
                msgList.add(message.build());
            });

            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            BarrageProto.WebClientBarrageHistoryListResp.Builder resp = BarrageProto.WebClientBarrageHistoryListResp.newBuilder();
            resp.addAllList(msgList);
            builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_HISTORY_RESP);
            builder.setBytesData(resp.build().toByteString());

            ctx.writeAndFlush(builder);
        } catch (Exception e) {
            log.error("[Exception]-[BarrageHistoryListServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
