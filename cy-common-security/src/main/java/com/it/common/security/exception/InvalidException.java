
package com.it.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.it.common.security.component.cyAuth2ExceptionSerializer;

/**
 * @author tony
 * @date 2018/7/8
 */
@JsonSerialize(using = cyAuth2ExceptionSerializer.class)
public class InvalidException extends zykjAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}
