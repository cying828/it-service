package com.it.proxy.JProxy;

/**
 * @Author Cying
 * @Date 2022/7/28 21:27
 * @Description
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("数据已经保存-----");
    }
}
