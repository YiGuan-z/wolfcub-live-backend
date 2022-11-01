package com.cqsd.data.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Report {
    /** 主键*/
    @TableId(type = IdType.AUTO)

    private Long id;

    /** 关联用户Id*/
    private User userId;

    /** 关联的弹幕Id*/
    private BulletMsg bulletId;

    /** 举报时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT + 8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reportDay;

    /** 举报内容*/
    private String reportContent;

    /** 状态(0开启,1禁用)*/
    private Integer status;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + userId +
                ", bulletId=" + bulletId +
                ", reportDay=" + reportDay +
                ", reportContent='" + reportContent + '\'' +
                ", status=" + status +
                '}';
    }
}