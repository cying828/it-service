
package com.it.common.security.annotation;

import com.it.common.security.component.cyResourceServerAutoConfiguration;
import com.it.common.security.component.cySecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author tony
 * @date 2018/11/10
 * <p>
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({cyResourceServerAutoConfiguration.class, cySecurityBeanDefinitionRegistrar.class})
public @interface EnablecyResourceServer {

}
