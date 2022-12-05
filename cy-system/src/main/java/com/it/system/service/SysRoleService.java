package com.it.system.service;

import com.it.common.api.model.system.SysRole;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/8/5 10:10
 * @Description
 */
public interface SysRoleService {
    List<SysRole> findRolesByUserId(String userId);
}
