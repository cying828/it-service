
package com.it.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.it.common.security.component.cyAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author tony
 * @date 2018/7/8
 */
@JsonSerialize(using = cyAuth2ExceptionSerializer.class)
public class ServerErrorException extends zykjAuth2Exception {

	public ServerErrorException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "server_error";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

}
