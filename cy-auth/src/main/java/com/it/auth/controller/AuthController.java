package com.it.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cying
 * @Date 2022/6/30 11:50
 * @Description
 */
@RestController
public class AuthController {
    @GetMapping("/test")
    public String get() {
        return "恭喜你访问成功！！！";
    }
}
