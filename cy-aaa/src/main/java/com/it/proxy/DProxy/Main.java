package com.it.proxy.DProxy;

/**
 * @Author Cying
 * @Date 2022/7/28 21:38
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        Profession teacher = new Teacher();

//        ProfessionProxy professionProxy = new ProfessionProxy();
//        professionProxy.setProfession(teacher);
//
//        Profession proxy = (Profession) professionProxy.getProxyInstance();

        ProxyFactory proxyFactory = new ProxyFactory(teacher);
        Profession proxy = (Profession) proxyFactory.getProxyInstance();

        proxy.work();
        proxy.salary();
    }
}
