package com.it.system;

import com.it.common.security.annotation.EnablecyFeignClients;
import com.it.common.security.annotation.EnablecyResourceServer;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Author Cying
 * @Date 2022/6/20 11:03
 * @Description
 */

@SpringCloudApplication
@ComponentScan({
        "com.it.common.api.sentinel",
        "com.it.system"})
@EnablecyFeignClients
@EnablecyResourceServer
@EnableAutoDataSourceProxy
//@RibbonClient(name = "cy-system-consume1",configuration = MyBalance.class)
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }
}
