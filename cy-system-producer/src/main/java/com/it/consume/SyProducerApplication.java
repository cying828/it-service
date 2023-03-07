package com.it.consume;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Cying
 * @Date 2022/6/24 17:26
 * @Description
 */
@SpringCloudApplication
@ComponentScan({
        "com.it.consume",
        "com.it.message"})
public class SyProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SyProducerApplication.class);
    }
}
