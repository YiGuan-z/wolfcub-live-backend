package com.cqsd.socket.core.utils;

import cn.hutool.json.JSONUtil;

import com.cqsd.socket.core.bo.BarrageMsgBo;
import com.cqsd.socket.core.consts.BarrageCacheKeyConst;
import com.cqsd.socket.core.consts.BarrageVideoConst;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BarrageContentUtils {

    public static BarrageMsgBo getContext(List<BarrageMsgBo> msgBos) {
        Random random = new Random();
        int i = random.nextInt(msgBos.size());
        return msgBos.get(i);
    }

    public static BarrageMsgBo getContext() {
        List<String> barrages = BarrageCacheUtils.listGetAll(BarrageCacheKeyConst.BARRAGE_TOTAL_MSG_KEY + BarrageVideoConst.VIDEO_ID);
        List<BarrageMsgBo> barrageMsgList = new ArrayList<>(barrages.size());
        barrages.forEach(v -> {
            BarrageMsgBo msgBo = JSONUtil.toBean(v, BarrageMsgBo.class);
            barrageMsgList.add(msgBo);
        });
        return getContext(barrageMsgList);
    }

}
