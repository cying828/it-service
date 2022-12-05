package com.it.system.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author Cying
 * @Date 2022/6/26 10:15
 * @Description
 */
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestT() {
        return new RestTemplate();
    }
}
