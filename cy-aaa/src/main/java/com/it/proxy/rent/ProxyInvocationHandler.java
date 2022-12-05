package com.it.proxy.rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Cying
 * @Date 2022/7/28 21:10
 * @Description
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Rent rent;

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),rent.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质，就是使用反射机制实现
        seeHouse();
        Object result = method.invoke(rent, args);
        fare();
        return result;
    }


    public void seeHouse() {
        System.out.println("中介带看房子");
    }
    public void fare() {
        System.out.println("收中介费");
    }


}
