
package com.it.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.it.common.security.component.cyAuth2ExceptionSerializer;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author tony
 * @date 2018/7/8
 * 自定义OAuth2Exception
 */
@JsonSerialize(using = cyAuth2ExceptionSerializer.class)
public class zykjAuth2Exception extends OAuth2Exception {
	@Getter
	private String errorCode;

	public zykjAuth2Exception(String msg) {
		super(msg);
	}

	public zykjAuth2Exception(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
