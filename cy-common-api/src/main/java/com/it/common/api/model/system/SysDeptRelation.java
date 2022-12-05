package com.it.common.api.model.system;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门间关系
 */
@Data
public class SysDeptRelation {

	/**
	 * 祖先节点，父节点
	 */
	private String ancestor;
	/**
	 * 后代节点，子节点
	 */
	private String descendant;

}
