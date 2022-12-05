package com.it.order;

import com.it.common.security.annotation.EnablecyFeignClients;
import com.it.common.security.annotation.EnablecyResourceServer;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Cying
 * @Date 2022/6/27 14:39
 * @Description
 */
@SpringCloudApplication
@EnablecyFeignClients
@ComponentScan({
        "com.it.common.api.sentinel",
        "com.it.order",
        "com.it.common.data"})
@EnablecyResourceServer
@EnableAutoDataSourceProxy
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }
}
