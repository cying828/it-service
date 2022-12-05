package com.it.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cying
 * @Date 2022/8/1 11:26
 * @Description
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @GetMapping("/get")
    public String get() {

        return "访问成功！！！";
    }
}
