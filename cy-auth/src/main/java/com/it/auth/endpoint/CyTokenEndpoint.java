package com.it.auth.endpoint;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import com.it.common.data.tenant.TenantContextHolder;
import com.it.common.security.util.SecurityUtils;
import com.it.common.core.constant.CommonConstants;
import com.it.common.core.constant.PaginationConstants;
import com.it.common.core.constant.SecurityConstants;
import com.it.common.core.util.R;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tony
 * @date 2018/6/24
 * 删除token端点
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class CyTokenEndpoint {
	private static final String cy_OAUTH_ACCESS = SecurityConstants.cy_PREFIX + SecurityConstants.OAUTH_PREFIX + "auth_to_access:";
	private final ClientDetailsService clientDetailsService;
	private final RedisTemplate cyRedisTemplate;
	private final TokenStore tokenStore;
	private final CacheManager cacheManager;

	/**
	 * 认证页面
	 *
	 * @param modelAndView
	 * @return ModelAndView
	 */
	@GetMapping("/login")
	public ModelAndView require(ModelAndView modelAndView) {
		modelAndView.setViewName("ftl/login");
		return modelAndView;
	}

	/**
	 * 确认授权页面
	 *
	 * @param request
	 * @param session
	 * @param modelAndView
	 * @return
	 */
	@GetMapping("/confirm_access")
	public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
		Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
		modelAndView.addObject("scopeList", scopeList.keySet());

		Object auth = session.getAttribute("authorizationRequest");
		if (auth != null) {
			AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
			modelAndView.addObject("app", clientDetails.getAdditionalInformation());
			modelAndView.addObject("user", SecurityUtils.getUser());
		}
		modelAndView.setViewName("ftl/confirm");
		return modelAndView;
	}

	/**
	 * 退出token
	 *
	 * @param authHeader Authorization
	 */
	@DeleteMapping("/logout")
	public R logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
		if (StrUtil.isBlank(authHeader)) {
			return R.builder()
					.code(CommonConstants.FAIL)
					.data(Boolean.FALSE)
					.msg("退出失败，token 为空").build();
		}

		String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
		OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
		if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
			return R.builder()
					.code(CommonConstants.FAIL)
					.data(Boolean.FALSE)
					.msg("退出失败，token 无效").build();
		}

		OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
		cacheManager.getCache("user_details")
				.evict(auth2Authentication.getName());
		tokenStore.removeAccessToken(accessToken);
		return new R<>(Boolean.TRUE);
	}

	/**
	 * 令牌管理调用
	 *
	 * @param token token
	 * @return
	 */
	@DeleteMapping("/{token}")
	public R<Boolean> delToken(@PathVariable("token") String token) {
		OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
		tokenStore.removeAccessToken(oAuth2AccessToken);
		return new R<>();
	}


	/**
	 * 查询token
	 *
	 * @param params 分页参数
	 * @return
	 */
	@PostMapping("/page")
	public R<Page> tokenList(@RequestBody Map<String, Object> params) {
		//根据分页参数获取对应数据
		String key = String.format("%s*:%s", cy_OAUTH_ACCESS, TenantContextHolder.getTenantId());
		List<String> pages = findKeysForPage(key, MapUtil.getInt(params, PaginationConstants.CURRENT)
				, MapUtil.getInt(params, PaginationConstants.SIZE));
//
		cyRedisTemplate.setKeySerializer(new StringRedisSerializer());
		cyRedisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		Page result = new Page(MapUtil.getInt(params, PaginationConstants.CURRENT), MapUtil.getInt(params, PaginationConstants.SIZE));
//		result.setRecords(cyRedisTemplate.opsForValue().multiGet(pages));
//		result.setTotal(Long.valueOf(cyRedisTemplate.keys(key).size()));
		return new R<>(result);
	}


	private List<String> findKeysForPage(String patternKey, int pageNum, int pageSize) {
//		ScanOptions options = ScanOptions.scanOptions().match(patternKey).build();
//		RedisSerializer<String> redisSerializer = (RedisSerializer<String>) cyRedisTemplate.getKeySerializer();
//		Cursor cursor = (Cursor) cyRedisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
		List<String> result = new ArrayList<>();
		int tmpIndex = 0;
		int startIndex = (pageNum - 1) * pageSize;
		int end = pageNum * pageSize;

//		assert cursor != null;
//		while (cursor.hasNext()) {
//			if (tmpIndex >= startIndex && tmpIndex < end) {
//				result.add(cursor.next().toString());
//				tmpIndex++;
//				continue;
//			}
//			if (tmpIndex >= end) {
//				break;
//			}
//			tmpIndex++;
//			cursor.next();
//		}
		return result;
	}
}
