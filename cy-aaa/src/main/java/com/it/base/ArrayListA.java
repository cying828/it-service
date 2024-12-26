package com.it.base;

import com.it.model.Dog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayListA {
    public static void main(String[] args) {
        //创建ArrayList对象 , 并存储狗狗
        List dogs = new ArrayList();

        dogs.add(new Dog("小狗一号"));
        dogs.add(new Dog("小狗二号"));
        dogs.add(new Dog("小狗三号"));
        dogs.add(2,new Dog("小狗四号"));// 添加到指定位置

        // .size() ： ArrayList大小
        System.out.println("共计有" + dogs.size() + "条狗狗。");
        System.out.println("分别是：");

        // .get(i) ： 逐个获取个元素
        for (int i = 0; i < dogs.size(); i++) {
            Dog dog = (Dog) dogs.get(i);
            System.out.println(dog.getName());
        }

        dogs.remove(2);

        Map<String,Integer> mp = new HashMap<>(2);
        mp.put("1",1);
        mp.put("2",1);
        mp.put("3",1);
    }
}
