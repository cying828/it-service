package com.it.system.service;

import com.it.common.api.model.system.SysUser;
import com.it.common.api.model.UserInfo;

/**
 * @Author Cying
 * @Date 2022/8/5 9:59
 * @Description
 */
public interface SysUserService {

    /**
     * 查询用户信息
     *
     * @param sysUser 用户
     * @return userInfo
     */
    UserInfo findUserInfo(SysUser sysUser);

    SysUser getOne(String username);
}
