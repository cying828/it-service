package com.it.proxy.DProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Cying
 * @Date 2022/7/28 21:40
 * @Description
 */
public class ProfessionProxy implements InvocationHandler {

    private Profession profession;

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),profession.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = method.invoke(profession, args);

        return result;
    }
}
