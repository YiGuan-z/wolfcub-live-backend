package com.cqsd.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 代星宇
 * @date 2022/10/17 17:02
 * @Version cn.wolfcode.vo
 */
@Setter
@Getter
public class Today {
    /**
     *今日注册
     */
    private Integer register=0;
    /**
     * 今日弹幕
     */
    private Integer barrage=0;
    /**
     *今日举报
     */
    private Integer tipOff=0;
    /**
     * 当前在线
     *
     */
    private Integer online=0;
}
