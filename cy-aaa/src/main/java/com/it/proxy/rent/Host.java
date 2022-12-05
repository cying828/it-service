package com.it.proxy.rent;

/**
 * @Author Cying
 * @Date 2022/7/28 21:09
 * @Description
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}
