package com.it.auth;

import com.it.common.security.annotation.EnablecyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Cying
 * @Date 2022/6/29 13:05
 * @Description
 */
@SpringBootApplication(scanBasePackages = {"com.it.auth","com.it.common.core","com.it.common.data"})
@EnablecyFeignClients
@EnableDiscoveryClient
@EnableCaching
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
