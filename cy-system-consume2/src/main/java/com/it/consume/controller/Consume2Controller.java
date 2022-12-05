package com.it.consume.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cying
 * @Date 2022/6/24 17:23
 * @Description
 */
@RestController
@RequestMapping("/consume")
public class Consume2Controller {

    @RequestMapping("/getOne")
    public String getOne() {
        System.out.println("我是consume2===========");
        return "我是consume2";
    }

}
