package com.it.system.mapper;

import com.it.common.api.model.system.SysRole;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/8/5 10:16
 * @Description
 */
public interface SysRoleMapper {

    /**
     * 通过用户ID，查询角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(String userId);
}
