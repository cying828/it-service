package com.it.common.api.model.system;

import com.it.common.core.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典表
 *
 * @author tony
 * @date 2019/03/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDict extends BaseModel<SysDict> {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	private String dictId;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 备注信息
	 */
	private String remarks;

}
