package com.it.gateway;

import com.it.common.gateway.annotation.EnablezykjDynamicRoute;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Cying
 * @Date 2022/6/28 11:30
 * @Description
 */
@SpringBootApplication
@EnablezykjDynamicRoute
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}
