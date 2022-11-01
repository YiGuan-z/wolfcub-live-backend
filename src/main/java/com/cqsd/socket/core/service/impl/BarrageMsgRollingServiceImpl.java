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
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_MSG_ROLLING_REQ)
@Slf4j
public class BarrageMsgRollingServiceImpl implements IBarrageMsgTypeService {

    private final IBulletMsgService barrageMsgService;

    public BarrageMsgRollingServiceImpl(IBulletMsgService barrageMsgService) {
        this.barrageMsgService = barrageMsgService;
    }


    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientBarrageMsgRollingReq msgRollingReq = BarrageProto.WebClientBarrageMsgRollingReq.parseFrom(barrage.getBytesData());
            log.info("[Req]-[BarrageMsgRollingServiceImpl]-[dealWithBarrageMessage]-[params:{}]", TextFormat.printToUnicodeString(msgRollingReq));

            String videoId = msgRollingReq.getVideoId();
            int currentVideoTime = msgRollingReq.getCurrentVideoTime();
            List<BulletMsg> barrageMsgList = barrageMsgService.getRollingMessages(videoId, currentVideoTime);

            List<BarrageProto.BarrageHistoryMessage> msgList = new ArrayList<>();
            barrageMsgList.forEach(v -> {
                BarrageProto.BarrageHistoryMessage.Builder message = BarrageProto.BarrageHistoryMessage.newBuilder();
                message.setMsg(v.getColor());
                message.setMsgPosition(v.getPosition());
                message.setCreateTime(BarrageDateUtils.dateToString(v.getReleaseTime(), BarrageDateUtils.DateType.PURE_DATE_MD_HM_PATTERN));
                message.setMsgColor(v.getColor());
                message.setSendTime(BarrageDateUtils.secondToNormTime(ObjectUtil.defaultIfNull(v.getVideoTime(), 0)));
                message.setMsg(v.getContent());
                msgList.add(message.build());
            });

            BarrageProto.Barrage.Builder builder = BarrageProto.Barrage.newBuilder();
            BarrageProto.WebClientBarrageMsgRollingResp.Builder resp = BarrageProto.WebClientBarrageMsgRollingResp.newBuilder();
            resp.addAllList(msgList);
            builder.setMsgType(BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_MSG_ROLLING_RESP);
            builder.setBytesData(resp.build().toByteString());

            ctx.writeAndFlush(builder);
        } catch (Exception e) {
            log.error("[Exception]-[BarrageMsgRollingServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }
}
