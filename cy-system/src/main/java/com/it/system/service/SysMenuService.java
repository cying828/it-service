package com.it.system.service;

import com.it.common.api.model.system.vo.MenuVO;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/8/5 10:10
 * @Description
 */
public interface SysMenuService {
    /**
     * 通过角色编号查询URL 权限
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<MenuVO> findMenuByRoleId(String roleId);
}
