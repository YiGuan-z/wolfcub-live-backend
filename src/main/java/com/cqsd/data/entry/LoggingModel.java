package com.cqsd.data.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public class LoggingModel {
	@TableId(type = IdType.AUTO)
	
	private Integer id;
	private String level;
	private String msg;
	
	@Override
	public String toString() {
		return new StringJoiner(", ", LoggingModel.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("level='" + level + "'")
				.add("msg='" + msg + "'")
				.toString();
	}
}
