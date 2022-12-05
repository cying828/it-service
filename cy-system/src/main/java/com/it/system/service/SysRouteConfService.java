
package com.it.system.service;

import cn.hutool.json.JSONArray;
import com.it.common.api.model.SysRouteConf;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 路由
 *
 * @author tony
 * @date 2018-11-06 10:17:18
 */
public interface SysRouteConfService {

	/**
	 * 获取全部路由
	 * <p>
	 * RedisRouteDefinitionWriter.java
	 * PropertiesRouteDefinitionLocator.java
	 *
	 * @return
	 */
	List<SysRouteConf> routes();

	/**
	 * 更新路由信息
	 *
	 * @param routes 路由信息
	 * @return
	 */
	Mono<Void> updateRoutes(JSONArray routes);
}

