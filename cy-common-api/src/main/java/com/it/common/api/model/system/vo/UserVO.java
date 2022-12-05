package com.it.common.api.model.system.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.it.common.api.model.system.SysRole;
import com.it.common.core.model.BaseModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author tony
 * @date 2017/10/29
 */
@Data
public class UserVO extends BaseModel<UserVO> {
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
	@JsonIgnore
	private String password;
	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 机构名称
	 */
	private String orgName;
	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 随机盐
	 */
	private String salt;

	/**
	 * 微信openid
	 */
	private String wxOpenid;

	/**
	 * 身份证号
	 */
	private String idCode;
	/**
	 * 职位
	 */
	private String job;


	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 0-正常，1-删除
	 */
	private String delFlag;

	/**
	 * 锁定标记
	 */
	private String lockFlag;
	/**
	 * 简介
	 */
	private String phone;
	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 是否重置密码{Y:需要重置;N:不需要重置}
	 */
	private String isRest;

	/**
	 * 角色列表
	 */
	private List<SysRole> roleList;
}
