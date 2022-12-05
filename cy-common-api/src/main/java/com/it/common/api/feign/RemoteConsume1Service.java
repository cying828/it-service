package com.it.common.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Cying
 * @Date 2022/6/24 17:36
 * @Description
 */
@FeignClient(contextId = "remoteConsume1Service",value = "cy-system-consume1")
public interface RemoteConsume1Service {
    @RequestMapping("/consume1/getOne")
    String getOne();
}
