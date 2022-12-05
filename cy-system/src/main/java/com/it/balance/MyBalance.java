package com.it.balance;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Cying
 * @Date 2022/6/27 10:25
 * @Description
 */
@Configuration
public class MyBalance {

    @Bean
    public IRule myRule() {
        return new RandomRule();
    }


}
