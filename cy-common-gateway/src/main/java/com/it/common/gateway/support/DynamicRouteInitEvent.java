
package com.it.common.gateway.support;

import org.springframework.context.ApplicationEvent;

/**
 * <p>
 * 路由初始化事件
 */
public class DynamicRouteInitEvent extends ApplicationEvent {
	public DynamicRouteInitEvent(Object source) {
		super(source);
	}
}
