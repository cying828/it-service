package com.it.system.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.it.common.api.model.UserInfo;
import com.it.common.api.model.system.SysRole;
import com.it.common.api.model.system.SysUser;
import com.it.common.api.model.system.vo.MenuVO;
import com.it.system.mapper.SysUserMapper;
import com.it.system.service.SysMenuService;
import com.it.system.service.SysRoleService;
import com.it.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Cying
 * @Date 2022/8/5 10:00
 * @Description
 */
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public UserInfo findUserInfo(SysUser sysUser) {
        UserInfo userInfo = new UserInfo();
        userInfo.setSysUser(sysUser);
        //设置角色列表  （ID）
        List<SysRole> roles = sysRoleService.findRolesByUserId(sysUser.getUserId());
        List<String> roleIds = roles.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        userInfo.setRoles(ArrayUtil.toArray(roleIds, String.class));

        //设置权限列表（menu.permission）
        Set<String> permissions = new HashSet<>();
        roleIds.forEach(roleId -> {
            List<String> permissionList = sysMenuService.findMenuByRoleId(roleId)
                    .stream()
                    .filter(menuVo -> StringUtils.isNotEmpty(menuVo.getPermission()))
                    .map(MenuVO::getPermission)
                    .collect(Collectors.toList());
            permissions.addAll(permissionList);
        });
        userInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
        return userInfo;
    }

    @Override
    public SysUser getOne(String username) {
        return sysUserMapper.getOne(username);
    }
}
