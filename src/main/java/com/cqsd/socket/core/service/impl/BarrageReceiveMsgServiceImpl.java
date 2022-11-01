package com.cqsd.socket.core.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

import com.cqsd.data.entry.BulletMsg;
import com.cqsd.data.entry.User;
import com.cqsd.data.entry.Video;
import com.cqsd.data.serivce.BulletMsgService;
import com.cqsd.socket.core.anno.BarrageAnnotation;
import com.cqsd.socket.core.bo.BarrageMsgBo;
import com.cqsd.socket.core.consts.BarrageCacheKeyConst;
import com.cqsd.socket.core.consts.BarrageMsgTypeConst;
import com.cqsd.socket.core.consts.BarrageVideoConst;
import com.cqsd.socket.core.netty.proto.BarrageProto;
import com.cqsd.socket.core.service.IBarrageMsgSendToClientService;
import com.cqsd.socket.core.service.IBarrageMsgTypeService;
import com.cqsd.socket.core.utils.BarrageCacheUtils;
import com.cqsd.socket.core.utils.BarrageConnectInfoUtils;
import com.cqsd.socket.core.utils.BarrageMsgSensitiveUtils;
import com.google.protobuf.TextFormat;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @Desc 服务器接收客户端发送过来的弹幕信息
 **/
@Service
@BarrageAnnotation(msgType = BarrageMsgTypeConst.WEB_CLIENT_BARRAGE_REQ)
@Slf4j
public class BarrageReceiveMsgServiceImpl implements IBarrageMsgTypeService {

    private final IBarrageMsgSendToClientService barrageSendMsgToClientService;
    private final BulletMsgService msgService;

    public BarrageReceiveMsgServiceImpl(IBarrageMsgSendToClientService barrageSendMsgToClientService, BulletMsgService msgService) {
        this.barrageSendMsgToClientService = barrageSendMsgToClientService;
        this.msgService = msgService;
    }


    @Override
    public void dealWithBarrageMessage(BarrageProto.Barrage barrage, ChannelHandlerContext ctx) {
        try {
            BarrageProto.WebClientSendBarrageReq clientSendBarrage = BarrageProto.WebClientSendBarrageReq.parseFrom(barrage.getBytesData());
            log.info("[Req]-[BarrageReceiveMsgServiceImpl]-[dealWithBarrageMessage]-[params{}]", TextFormat.printToUnicodeString(clientSendBarrage));
            String msg = StrUtil.blankToDefault(clientSendBarrage.getMsg(), "我们都是追梦人");
            String msgColor = StrUtil.blankToDefault(clientSendBarrage.getMsgColor(), "#FFFFFF");
            String userId = StrUtil.blankToDefault(clientSendBarrage.getUserId(), "");
            String videoId = StrUtil.blankToDefault(clientSendBarrage.getVideoId(), "");
            int msgPosition = ObjectUtil.defaultIfNull(clientSendBarrage.getMsgPosition(), 0);
            int msgVideoTime = ObjectUtil.defaultIfNull(clientSendBarrage.getMsgVideoTime(), 1);

            // 进行敏感词汇过滤替换
            msg = filterSensitiveMsg(msg);

            BulletMsg bulletMsg = new BulletMsg();
            bulletMsg.setColor(msgColor);
            bulletMsg.setContent(msg);
            bulletMsg.setPosition(msgPosition);
            User user = new User();
            user.setId(Long.parseLong(userId));
            bulletMsg.setAuthor(user);
            Video video = new Video();
            video.setId(videoId);
            bulletMsg.setVideo(video);
            bulletMsg.setVideoTime(0 < msgVideoTime ? msgVideoTime : 1);
            bulletMsg.setReleaseTime(new Date());
            msgService.save(bulletMsg);

            BarrageMsgBo barrageMsgBo = new BarrageMsgBo(msg, msgColor, msgPosition, userId, videoId);
            BarrageCacheUtils.listPush(BarrageCacheKeyConst.BARRAGE_TOTAL_MSG_KEY + BarrageVideoConst.VIDEO_ID, JSONUtil.toJsonStr(barrageMsgBo));

            notifyOtherUser(videoId, ctx, barrageMsgBo);

        } catch (Exception e) {
            log.error("[Exception]-[BarrageReceiveMsgServiceImpl]-[dealWithBarrageMessage]", e);
        }
    }


    private void notifyOtherUser(String videId, ChannelHandlerContext ctx, BarrageMsgBo barrageMsgBo) {
        List<ChannelHandlerContext> channelHandlerContextLists = BarrageConnectInfoUtils.getChannelHandlerContextListByVideId(videId);
        if (CollectionUtils.isEmpty(channelHandlerContextLists)) {
            return;
        }
        channelHandlerContextLists.stream()
                .filter(v -> !v.equals(ctx) && v.channel().isActive())
                .forEach(v -> barrageSendMsgToClientService.sendMsg(barrageMsgBo, v));
    }

    private String filterSensitiveMsg(String msg) {
        return BarrageMsgSensitiveUtils.replaceSensitiveMsg(msg);
    }

}
