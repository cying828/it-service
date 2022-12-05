

package com.it.common.api.model.system;

import com.it.common.core.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author tony
 * @since 2017-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseModel<SysRole> {

	private static final long serialVersionUID = 1L;

	private String roleId;

	@NotBlank(message = "角色标识 不能为空")
	private String roleCode;

	@NotBlank(message = "角色名称 不能为空")
	private String roleName;

	private String roleDesc;

	/**
	 * 数据权限类型，对应字典表  DictConstants.DS_TYPE   RoleConstants.DsType
	 */
	@NotNull(message = "数据权限类型 不能为空")
	private Integer dsType;

	/**
	 * 数据权限范围
	 */
	private String dsScope;
	/**
	 * 项目权限类型 [0-全部项目;1-部分项目;]   RoleConstants.ProjectType
	 */
	private String projectType;
	/**
	 * 机构类型，对应字典表  DictConstants.ORG_TYPE
	 */
	private String orgType;

}
