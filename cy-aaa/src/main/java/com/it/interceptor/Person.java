package com.it.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author Cying
 * @Date 2022/7/28 22:27
 * @Description
 */
public class Person {

    private HandlerInterceptor interceptor;

    public Person(HandlerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void eat() {
        System.out.println("开饭喽......");
    }


}
