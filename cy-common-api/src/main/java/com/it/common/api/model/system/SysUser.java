package com.it.common.api.model.system;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author tony
 * @since 2017-10-29
 */
@Data
public class SysUser {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 随机盐
	 */
	@JsonIgnore
	private String salt;
	/**
	 * 联系方式
	 */
	private String phone;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 微信openid
	 */
	private String wxOpenid;
	/**
	 * 是否重置密码{Y:需要重置;N:不需要重置}
	 */
	private String isRest;
	/**
	 * 用户状态：锁定标记  [0:正常;1:锁定;2:不可登录]
	 */
	private String lockFlag;
	/**
	 * 描述备注
	 */
	private String description;
	/**
	 * 职位
	 */
	private String job;

}
