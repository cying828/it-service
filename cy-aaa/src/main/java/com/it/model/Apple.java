package com.it.model;

/**
 * @Author Cying
 * @Date 2022/7/26 21:53
 * @Description
 */
public class Apple {

    private int number = 100;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "number=" + number +
                '}';
    }
}
