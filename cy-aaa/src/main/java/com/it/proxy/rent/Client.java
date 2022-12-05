package com.it.proxy.rent;

/**
 * @Author Cying
 * @Date 2022/7/28 21:16
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        Host host = new Host();

        ProxyInvocationHandler pro = new ProxyInvocationHandler();

        pro.setRent(host);

        Rent proxy = (Rent) pro.getProxy();

        proxy.rent();
    }
}
