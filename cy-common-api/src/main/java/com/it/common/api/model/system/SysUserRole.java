package com.it.common.api.model.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 用户角色表
 * </p>
 *
 * @author tony
 * @since 2017-10-29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 角色ID
     */
    private String roleId;

}
