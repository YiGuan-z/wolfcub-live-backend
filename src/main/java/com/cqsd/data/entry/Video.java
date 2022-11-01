package com.cqsd.data.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static com.cqsd.utils.Constants.DEFAULT_FORMAT_DATETIME_PATTERN;
import static com.cqsd.utils.Constants.DEFAULT_FORMAT_DATE_PATTERN;


@Getter
@Setter
public class Video {
    /**
     * 主键 id
     */
    @TableId(type = IdType.AUTO)

    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 描述
     */
    private String description;
    /**
     * 封面
     */
    private String coverUrl;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 作者
     */
    private Long authorId;
    /**
     * 点赞数量
     */
    private Integer likes;
    /**
     * 投币数
     */
    private Integer coins;
    /**
     * 分享数
     */
    private String shares;
    /**
     * 评论数
     */
    private Integer comments;
    /**
     *观看数
     */
    private Integer views;
    /**
     * 视频状态(0=未发布, 1=已发布, 2=已禁用)
     */
    private String status;

    /**
    * 是否删除(0=正常, 1=删除)
    */
    private Boolean deleted;
    /**
     * 发布时间
     */
    @JsonFormat(pattern = DEFAULT_FORMAT_DATETIME_PATTERN, timezone = DEFAULT_FORMAT_DATE_PATTERN)
    private Date releaseTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = DEFAULT_FORMAT_DATETIME_PATTERN, timezone = DEFAULT_FORMAT_DATE_PATTERN)
    private Date createdTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = DEFAULT_FORMAT_DATETIME_PATTERN, timezone = DEFAULT_FORMAT_DATE_PATTERN)
    private Date updatedTime;
}
