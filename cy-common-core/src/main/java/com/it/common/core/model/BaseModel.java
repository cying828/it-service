package com.it.common.core.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseModel<T> implements Serializable {

	/**
	 * 创建人ID
	 */
	protected String createId;

	/**
	 * 修改人ID
	 */
	protected String updateId;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime updateTime;

	/**
	 * 逻辑删除标识
	 */
	protected String delFlag;

}
