package com.it.auth.handler;

import cn.hutool.core.util.StrUtil;
import com.it.common.core.config.ValidateCodeConfig;
import com.it.common.core.constant.CacheConstants;
import com.it.common.security.handler.AbstractAuthenticationFailureEvenHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class CyAuthenticationFailureEvenHandler extends AbstractAuthenticationFailureEvenHandler {


    private final RedisTemplate cyRedisTemplate;
    private final ValidateCodeConfig validateCodeConfig;

    /**
     * 处理登录失败方法
     * <p>
     *
     * @param authenticationException 登录的authentication 对象
     * @param authentication          登录的authenticationException 对象
     */
    @Override
    public void handle(AuthenticationException authenticationException, Authentication authentication) {
        log.info("用户：{} 登录失败，异常：{}", authentication.getPrincipal(), authenticationException.getLocalizedMessage());

        // 判断当前redis 是否包含了这个用户名，如果有，则错误次数+1，如果没有，则错误次数设置为1
        // 设置300秒 过期时间
        String loginName = authentication.getPrincipal().toString();
        BoundValueOperations<String, Integer> ops = cyRedisTemplate.boundValueOps(StrUtil.format("{}{}", CacheConstants.LOGIN_COUNT, loginName));
        boolean flag = cyRedisTemplate.hasKey(StrUtil.format("{}{}", CacheConstants.LOGIN_COUNT, loginName));
        int count = flag ? (ops.get() + 1) : 1;
        ops.set(count);
        ops.expire(validateCodeConfig.getCodeValidateFailUntilTime(), TimeUnit.SECONDS);
    }
}
