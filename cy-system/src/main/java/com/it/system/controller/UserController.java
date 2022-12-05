package com.it.system.controller;

import com.it.common.api.model.system.SysUser;
import com.it.common.api.model.UserInfo;
import com.it.common.core.util.R;
import com.it.common.security.annotation.Inner;
import com.it.common.security.util.SecurityUtils;
import com.it.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Cying
 * @Date 2022/6/29 21:23
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private SysUserService userService;


    @GetMapping(value = {"/info"})
    public R info() {
        String username = SecurityUtils.getUser().getUsername();
        SysUser user = userService.getOne(username);
        if (user == null) {
            return new R<>(Boolean.FALSE, "获取当前用户信息失败");
        }
        return new R<>(userService.findUserInfo(user));
    }


    @Inner()
    @GetMapping("/info/{username}")
    public R info(@PathVariable String username) {
        SysUser user = userService.getOne(username);
        if (user == null) {
            return new R<>(Boolean.FALSE, String.format("用户信息为空 %s", username));
        }
        return new R<>(userService.findUserInfo(user));
    }
}
