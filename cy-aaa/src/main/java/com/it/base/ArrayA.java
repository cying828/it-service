package com.it.base;


import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 数组
 */
public class ArrayA {

    public static void main(String[] args) {

        int[] array = {1,2,3,5,6,7,11,15};

        int i = Arrays.binarySearch(array, 7);
        Arrays.fill(array,1,4,100);
        System.out.println(Arrays.toString(array));


        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("b");
        List<String> list2 = new ArrayList<>();
        list2.add("A");
        list2.add("B");
        Collection<String> subtract = CollUtil.subtract(list1, list2);
        System.out.println(subtract);
    }
}
