package com.it.proxy.JProxy;

/**
 * @Author Cying
 * @Date 2022/7/28 21:28
 * @Description  静态代理
 */
public class UserDaoProxy implements UserDao {

    private UserDao userDao;


    public UserDaoProxy(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("开始事务");
        userDao.save();
        System.out.println("提交事务");
    }
}
