package com.it.proxy.DProxy;

/**
 * @Author Cying
 * @Date 2022/7/28 21:37
 * @Description
 */
public class Teacher implements Profession {
    @Override
    public void work() {
        System.out.println("老师----教书-----");
    }

    @Override
    public void salary() {
        System.out.println("人民教师===发薪水了");
    }
}
