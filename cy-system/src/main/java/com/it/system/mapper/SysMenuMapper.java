package com.it.system.mapper;

import com.it.common.api.model.system.vo.MenuVO;

import java.util.List;

/**
 * @Author Cying
 * @Date 2022/8/5 10:48
 * @Description
 */
public interface SysMenuMapper {
    List<MenuVO> listMenusByRoleId(String roleId);
}
