
package com.it.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.it.common.core.constant.CommonConstants;
import com.it.common.security.exception.zykjAuth2Exception;
import lombok.SneakyThrows;

/**
 * @author tony
 * @date 2018/11/16
 * <p>
 * OAuth2 异常格式化
 */
public class cyAuth2ExceptionSerializer extends StdSerializer<zykjAuth2Exception> {
	public cyAuth2ExceptionSerializer() {
		super(zykjAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(zykjAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", CommonConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeStringField("data", value.getErrorCode());
		gen.writeEndObject();
	}
}
