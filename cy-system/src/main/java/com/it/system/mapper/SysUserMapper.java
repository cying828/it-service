
package com.it.system.mapper;

import com.it.common.api.model.system.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author tony
 * @since 2017-10-29
 */
public interface SysUserMapper {
    /**
     * 通过用户名查询用户信息（含有角色信息）
     *
     * @param username 用户名
     * @return userVo
     */
    SysUser getOne(@Param("username") String username);

}
