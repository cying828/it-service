package com.it.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Cying
 * @Date 2022/7/28 22:13
 * @Description
 */
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("这是前置========");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("你是干什么的--------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("这是后置========");
    }

}
