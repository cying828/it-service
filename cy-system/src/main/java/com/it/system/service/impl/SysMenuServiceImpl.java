package com.it.system.service.impl;

import com.it.common.api.model.system.vo.MenuVO;
import com.it.common.core.constant.CacheConstants;
import com.it.system.mapper.SysMenuMapper;
import com.it.system.service.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Cying
 * @Date 2022/8/5 10:11
 * @Description
 */
@Slf4j
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
    public List<MenuVO> findMenuByRoleId(String roleId) {
        return sysMenuMapper.listMenusByRoleId(roleId);
    }
}
