
package com.it.common.gateway.annotation;

import com.it.common.gateway.configuration.DynamicRouteAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <p>
 * 开启zykj 动态路由
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(DynamicRouteAutoConfiguration.class)
public @interface EnablezykjDynamicRoute {
}
