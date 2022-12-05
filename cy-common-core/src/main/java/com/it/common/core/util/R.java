
package com.it.common.core.util;

import com.it.common.core.constant.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @param <T>
 * @author tony
 */
@Builder
@ToString
@Accessors(chain = true)
@ApiModel(description = "响应信息主体")
@AllArgsConstructor
public class R<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回标记：成功标记=0，失败标记=1")
	private int code = CommonConstants.SUCCESS;

	@Getter
	@Setter
	@ApiModelProperty(value = "返回信息")
	private String msg = "success";


	@Getter
	@Setter
	@ApiModelProperty(value = "数据")
	private T data;

	public R() {
		super();
	}

	public R(T data) {
		super();
		this.data = data;
	}

	public R(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public R(Throwable e) {
		super();
		this.msg = e.getMessage();
		this.code = CommonConstants.FAIL;
	}
}
