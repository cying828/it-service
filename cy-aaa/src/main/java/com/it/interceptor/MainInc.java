package com.it.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author Cying
 * @Date 2022/7/28 22:17
 * @Description
 */
public class MainInc {

    public static void main(String[] args) {
        HandlerInterceptor login = new LoginInterceptor();

        Person person = new Person(login);

        person.eat();

    }

}
