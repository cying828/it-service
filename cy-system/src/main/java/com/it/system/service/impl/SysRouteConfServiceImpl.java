
package com.it.system.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.it.common.api.model.SysRouteConf;
import com.it.common.core.constant.CacheConstants;
import com.it.common.core.constant.CommonConstants;
import com.it.common.gateway.support.DynamicRouteInitEvent;
import com.it.common.gateway.vo.RouteDefinitionVo;
import com.it.system.mapper.SysRouteConfMapper;
import com.it.system.service.SysRouteConfService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tony
 * @date 2018年11月06日10:27:55
 * <p>
 * 动态路由处理类
 */
@Slf4j
@AllArgsConstructor
@Service("sysRouteConfService")
public class SysRouteConfServiceImpl implements SysRouteConfService {
	private final RedisTemplate redisTemplate;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final SysRouteConfMapper sysRouteConfMapper;


	/**
	 * 获取全部路由
	 * <p>
	 * RedisRouteDefinitionWriter.java
	 * PropertiesRouteDefinitionLocator.java
	 *
	 * @return
	 */
	@Override
	public List<SysRouteConf> routes() {
		return sysRouteConfMapper.selectList();
	}

	/**
	 * 更新路由信息
	 *
	 * @param routes 路由信息
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Mono<Void> updateRoutes(JSONArray routes) {
		// 清空Redis 缓存
		Boolean result = redisTemplate.delete(CacheConstants.ROUTE_KEY);
		log.info("清空网关路由 {} ", result);

		// 遍历修改的routes，保存到Redis
		List<RouteDefinitionVo> routeDefinitionVoList = new ArrayList<>();

		try {
			routes.forEach(value -> {
				log.info("更新路由 ->{}", value);
				RouteDefinitionVo vo = new RouteDefinitionVo();
				Map<String, Object> map = (Map) value;

				Object id = map.get("routeId");
				if (id != null) {
					vo.setId(String.valueOf(id));
				}

				Object routeName = map.get("routeName");
				if (routeName != null) {
					vo.setRouteName(String.valueOf(routeName));
				}

				Object predicates = map.get("predicates");
				if (predicates != null) {
					JSONArray predicatesArray = (JSONArray) predicates;
					List<PredicateDefinition> predicateDefinitionList =
							predicatesArray.toList(PredicateDefinition.class);
					vo.setPredicates(predicateDefinitionList);
				}

				Object filters = map.get("filters");
				if (filters != null) {
					JSONArray filtersArray = (JSONArray) filters;
					List<FilterDefinition> filterDefinitionList
							= filtersArray.toList(FilterDefinition.class);
					vo.setFilters(filterDefinitionList);
				}

				Object uri = map.get("uri");
				if (uri != null) {
					vo.setUri(URI.create(String.valueOf(uri)));
				}

				Object order = map.get("order");
				if (order != null) {
					vo.setOrder(Integer.parseInt(String.valueOf(order)));
				}
				redisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(RouteDefinitionVo.class));
				redisTemplate.opsForHash().put(CacheConstants.ROUTE_KEY, vo.getId(), vo);
				routeDefinitionVoList.add(vo);
			});

			// 逻辑删除全部
			SysRouteConf condition = new SysRouteConf();
			condition.setDelFlag(CommonConstants.STATUS_NORMAL);
			sysRouteConfMapper.remove(condition);

			//插入生效路由
			List<SysRouteConf> routeConfList = routeDefinitionVoList.stream().map(vo -> {
				SysRouteConf routeConf = new SysRouteConf();
				routeConf.setRouteId(vo.getId());
				routeConf.setRouteName(vo.getRouteName());
				routeConf.setFilters(JSONUtil.toJsonStr(vo.getFilters()));
				routeConf.setPredicates(JSONUtil.toJsonStr(vo.getPredicates()));
				routeConf.setOrder(vo.getOrder());
				routeConf.setUri(vo.getUri().toString());
				return routeConf;
			}).collect(Collectors.toList());
			sysRouteConfMapper.saveList(routeConfList);
			log.debug("更新网关路由结束 ");

			this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));

		} catch (Exception e) {
			log.error("路由配置解析失败", e);
			// 回滚路由，重新加载即可
			this.applicationEventPublisher.publishEvent(new DynamicRouteInitEvent(this));
			// 抛出异常
			throw new RuntimeException(e);
		}
		return Mono.empty();
	}

}
