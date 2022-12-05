package com.it.proxy.JProxy;

/**
 * @Author Cying
 * @Date 2022/7/28 21:31
 * @Description
 */
public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        UserDaoProxy proxy = new UserDaoProxy(userDao);

        proxy.save();
    }
}
