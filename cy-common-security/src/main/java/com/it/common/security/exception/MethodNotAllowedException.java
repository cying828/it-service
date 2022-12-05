
package com.it.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.it.common.security.component.cyAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author tony
 * @date 2018/7/8
 */
@JsonSerialize(using = cyAuth2ExceptionSerializer.class)
public class MethodNotAllowedException extends zykjAuth2Exception {

	public MethodNotAllowedException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}
