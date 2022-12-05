package com.it.common.api.feign;

import com.it.common.api.model.UserInfo;
import com.it.common.core.constant.SecurityConstants;
import com.it.common.core.constant.ServiceNameConstants;
import com.it.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @param from     调用标志
     * @return R
     */
    @GetMapping("/user/info/{username}")
    R<UserInfo> info(@PathVariable("username") String username
            , @RequestHeader(SecurityConstants.FROM) String from);
}
