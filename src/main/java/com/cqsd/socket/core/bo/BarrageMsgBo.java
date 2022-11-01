package com.cqsd.socket.core.bo;

import lombok.Data;

@Data
public class BarrageMsgBo {

    private String msg;

    private String msgColor;

    private Integer msgPosition;


    private String userId;

    private String videoId;

    public BarrageMsgBo() {
    }

    public BarrageMsgBo(String msg, String msgColor, Integer msgPosition, String userId, String videoId) {
        this.msg = msg;
        this.msgColor = msgColor;
        this.msgPosition = msgPosition;
        this.userId = userId;
        this.videoId = videoId;
    }
}
