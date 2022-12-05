package com.it.system.service.impl;

import com.it.common.api.model.system.SysRole;
import com.it.system.mapper.SysRoleMapper;
import com.it.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
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
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> findRolesByUserId(String userId) {
        return sysRoleMapper.listRolesByUserId(userId);
    }
}
