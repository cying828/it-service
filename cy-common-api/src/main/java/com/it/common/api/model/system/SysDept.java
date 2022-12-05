package com.it.common.api.model.system;

import com.it.common.core.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseModel<SysDept> {

	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 上级部门ID
	 */
	private String parentId;
	/**
	 * 机构ID
	 */
	private String orgId;

}
