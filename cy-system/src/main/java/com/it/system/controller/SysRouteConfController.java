
package com.it.system.controller;

import cn.hutool.json.JSONArray;
import com.it.common.core.util.R;
import com.it.system.service.SysRouteConfService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 路由
 *
 * @author tony
 * @date 2018-11-06 10:17:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/route")
@Api(value = "route",tags = "动态路由管理模块")
public class SysRouteConfController {
	private final SysRouteConfService sysRouteConfService;


	/**
	 * 获取当前定义的路由信息
	 *
	 * @return
	 */
	@GetMapping
	public R listRoutes() {
		return new R<>(sysRouteConfService.routes());
	}

	/**
	 * 修改路由
	 *
	 * @param routes 路由定义
	 * @return
	 */
	@PutMapping
	public R updateRoutes(@RequestBody JSONArray routes) {
		return new R(sysRouteConfService.updateRoutes(routes));
	}

}
