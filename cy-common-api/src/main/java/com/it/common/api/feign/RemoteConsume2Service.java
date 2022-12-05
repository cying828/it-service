package com.it.common.api.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Cying
 * @Date 2022/6/24 17:36
 * @Description
 */
@FeignClient(contextId = "remoteConsume2Service",value = "cy-system-consume2")
public interface RemoteConsume2Service {
    @RequestMapping("/consume2/getOne")
    String getOne();
}
