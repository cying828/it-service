package com.it.auth.handler;

import cn.hutool.core.util.StrUtil;
import com.it.common.core.constant.CacheConstants;
import com.it.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import com.it.common.security.service.cyUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CyAuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

    private final RedisTemplate zykjRedisTemplate;

    /**
     * 处理登录成功方法
     * <p>
     * 获取到登录的authentication 对象
     *
     * @param authentication 登录对象
     */
    @Override
    public void handle(Authentication authentication) {
        log.info("用户：{} 登录成功", authentication.getPrincipal());

        String loginName = ((cyUser) authentication.getPrincipal()).getUsername();
        zykjRedisTemplate.delete(StrUtil.format("{}{}", CacheConstants.LOGIN_COUNT, loginName));
    }
}
