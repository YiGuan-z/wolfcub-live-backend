package com.cqsd.data.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 弹幕管理
 */
@Getter
@Setter
@TableName("t_bullet_msg")
public class BulletMsg implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 发布人
	 */
	private User author;
	/**
	 * 视频
	 */
	private Video video;
	
	/**
	 * 弹幕内容
	 */
	private String content;
	
	/**
	 * 弹幕位置
	 */
	private Integer position;
	
	/**
	 * 弹幕颜色
	 */
	private String color;
	
	/**
	 * 视频时间(秒)
	 */
	private Integer videoTime;
	
	/**
	 * 点赞数
	 */
	private Integer likes;
	
	/**
	 * 字体大小
	 */
	private String fontSize;
	
	/**
	 * 状态(0=正常, 1=禁用)
	 */
	private String status;
	
	/**
	 * 发布时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date releaseTime;
	
	private Integer reportNum;
}
