package com.it.common.security.component;

import cn.hutool.core.util.StrUtil;
import com.it.common.core.config.ValidateCodeConfig;
import com.it.common.core.constant.CacheConstants;
import com.it.common.core.constant.CommonConstants;
import com.it.common.core.util.R;
import com.it.common.security.exception.MethodNotAllowedException;
import com.it.common.security.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;


/**
 * @author tony
 * @date 2018/7/8
 * 异常处理,重写oauth 默认实现
 */
@Slf4j
public class cyWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

	private ThrowableAnalyzer throwableAnalyzer;
    private RedisTemplate zykjRedisTemplate;
    private ValidateCodeConfig validateCodeConfig;

    public cyWebResponseExceptionTranslator(RedisTemplate zykjRedisTemplate, ValidateCodeConfig validateCodeConfig) {
        this.zykjRedisTemplate = zykjRedisTemplate;
        this.validateCodeConfig = validateCodeConfig;
        this.throwableAnalyzer = new DefaultThrowableAnalyzer();
    }

    @Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) {

		// Try to extract a SpringSecurityException from the stacktrace
		Throwable[] causeChain = throwableAnalyzer.determineCauseChain(e);

		Exception ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType(AuthenticationException.class,
				causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new OAuth2Exception(e.getMessage(), e));
		}

		ase = (AccessDeniedException) throwableAnalyzer
				.getFirstThrowableOfType(AccessDeniedException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new OAuth2Exception(ase.getMessage(), ase));
		}

		ase = (InvalidGrantException) throwableAnalyzer
				.getFirstThrowableOfType(InvalidGrantException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new OAuth2Exception(ase.getMessage(), ase));
		}

		ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer
				.getFirstThrowableOfType(HttpRequestMethodNotSupportedException.class, causeChain);
		if (ase != null) {
			return handleOAuth2Exception(new MethodNotAllowedException(ase.getMessage(), ase));
		}

		ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType(
				OAuth2Exception.class, causeChain);

		if (ase != null) {
			return handleOAuth2Exception((OAuth2Exception) ase);
		}

		return handleOAuth2Exception(new OAuth2Exception(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e));

	}

	private ResponseEntity<OAuth2Exception> handleOAuth2Exception(OAuth2Exception e) {

		int status = e.getHttpErrorCode();
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.CACHE_CONTROL, "no-store");
		headers.set(HttpHeaders.PRAGMA, "no-cache");
		if (status == HttpStatus.UNAUTHORIZED.value() || (e instanceof InsufficientScopeException)) {
			headers.set(HttpHeaders.WWW_AUTHENTICATE, String.format("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary()));
		}

		HttpServletRequest request = WebUtils.getRequest();
		String username = request.getParameter("username");
		BoundValueOperations<String, Integer> ops = zykjRedisTemplate.boundValueOps(StrUtil.format("{}{}",
				CacheConstants.LOGIN_COUNT, username));
		Integer count = ops.get();

		int codeValidateCount = validateCodeConfig.getCodeValidateCount();
		boolean needCode = count!=null && count >= codeValidateCount;
		HttpStatus httpStatus = HttpStatus.valueOf(needCode ? HttpStatus.PAYMENT_REQUIRED.value() : status);

		R res = R.builder().data(needCode).code(CommonConstants.FAIL).build();
		// 客户端异常直接返回客户端,不然无法解析
		if (e instanceof ClientAuthenticationException) {
			return new ResponseEntity(res, headers, httpStatus);
		}
		return new ResponseEntity(res, headers, httpStatus);

	}
}
