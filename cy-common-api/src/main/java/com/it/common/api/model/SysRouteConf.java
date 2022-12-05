

package com.it.common.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 路由
 *
 * @author tony
 * @date 2018-11-06 10:17:18
 */
@Data
public class SysRouteConf {
	private static final long serialVersionUID = 1L;
	private String routeConfId;
	/**
	 * 路由ID
	 */
	private String routeId;
	/**
	 * 路由名称
	 */
	private String routeName;
	/**
	 * 断言
	 */
	private String predicates;
	/**
	 * 过滤器
	 */
	private String filters;
	/**
	 * uri
	 */
	private String uri;
	/**
	 * 排序
	 */
	private Integer order;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 删除标识（0-正常,1-删除）
	 */
	private String delFlag;

}
