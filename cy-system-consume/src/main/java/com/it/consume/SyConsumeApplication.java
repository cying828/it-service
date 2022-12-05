package com.it.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Cying
 * @Date 2022/6/24 17:07
 * @Description
 */
@SpringBootApplication
@EnableFeignClients
public class SyConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SyConsumeApplication.class);
    }
}
