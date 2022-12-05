/*
 *
 *      Copyright (c) 2018-2025, tony All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the mingtian-group.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: tony (117332652@qq.com)
 *
 */

package com.it.common.api.model.system;

import com.it.common.core.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author tony
 * @since 2017-11-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseModel<SysMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@NotNull(message = "菜单ID不能为空")
	private String menuId;
	/**
	 * 菜单编号
	 */
	// @NotBlank(message = "菜单编号不能为空")
	private String code;
	/**
	 * 菜单名称
	 */
	@NotBlank(message = "菜单名称不能为空")
	private String name;
	/**
	 * 菜单权限标识
	 */
	private String permission;
	/**
	 * 前端URL
	 */
	private String path;
	/**
	 * 父菜单ID
	 */
	@NotNull(message = "菜单父ID不能为空")
	private String parentId;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * VUE页面
	 */
	private String component;
	/**
	 * 排序值
	 */
	private Integer sort;
	/**
	 * 路由缓冲
	 */
	private String keepAlive;
	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	@NotNull(message = "菜单类型不能为空")
	private String type;

	/**
	 * 客户端类型 （1:pc端、2:小程序端……）
	 */
	@NotNull(message = "类型不能为空")
	private String clientType;


}
