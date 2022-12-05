
package com.it.common.api.model.system.vo;

import com.it.common.core.model.BaseModel;
import lombok.Data;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author tony
 * @since 2017-11-08
 */
@Data
public class MenuVO extends BaseModel<MenuVO> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	private String menuId;
	/**
	 * 菜单编号
	 */
	private String code;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单权限标识
	 */
	private String permission;
	/**
	 * 父菜单ID
	 */
	private String parentId;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 一个路径
	 */
	private String path;
	/**
	 * VUE页面
	 */
	private String component;
	/**
	 * 排序值
	 */
	private Integer sort;
	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	private String type;
	/**
	 * 是否缓冲
	 */
	private String keepAlive;

	/**
	 * 客户端类型
	 */
	private String clientType;

	@Override
	public int hashCode() {
		return menuId.hashCode();
	}

	/**
	 * menuId 相同则相同
	 *
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuVO) {
			String targetMenuId = ((MenuVO) obj).getMenuId();
			return menuId.equals(targetMenuId);
		}
		return super.equals(obj);
	}
}
