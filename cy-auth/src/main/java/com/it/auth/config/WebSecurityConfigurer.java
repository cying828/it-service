package com.it.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.common.security.handler.MobileLoginSuccessHandler;
import com.it.common.security.mobile.MobileSecurityConfigurer;
import com.it.common.security.service.cyUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Author Cying
 * @Date 2022/6/29 18:22
 * @Description
 */
@Primary
@Order(90)
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private cyUserDetailsService userDetailsService;
    @Lazy
    @Autowired
    private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;

    //拦截请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * formLogin 开启表单登录方式
         * loginPage 自定义登录跳转地址 不设置默认/login,设置后需将设置地址设置为任何人可以访问，不然会重定向循环
         * loginProcessingUrl 登录提交的请求，不设置时和loginPage设置的地址一样，单独设置没有意义，还发现一个挺奇怪的点，当配置了successHandler时，如果没有在successHandler处理器中跳转其他地址，那么浏览器就会跳转到loginProcessingUrl配置的地址，这是个虚地址， 你可以没有该地址
         * successForwardUrl 认证成功的转发地址 ，如果没有设置则按照跳转到登陆前的地址。即会记忆地址，登陆后又到原地址。这里有个坑如果这个地址最终返回的是个视图则会报错 ，因为登陆是post请求，而mvc不允许post请求返回视图 ,只要要改为转发就行 。
         * defaultSuccessUrl 默认的认证成功跳转路径，这里和successForwardUrl的区别是，如果加上参数true效果是一样的，如果不加，则没有记忆地址时会跳到该地址。
         * authorizeRequests 请求授权
         * antMatchers 不需要权限认证得url
         * permitAll 用户可以任意访问
         * anyRequest 匹配所有请求路径
         * authenticated 认证后可以访问
         * csrf 跨域  csrf().disable() 关闭跨域请求防护
         *
         */

        http.formLogin()
                .loginPage("/token/login")
                .loginProcessingUrl("/token/form")
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/token/**",
                        "/actuator/**",
                        "/mobile/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    /**
     * 不拦截静态资源
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**");
    }

    @Bean
    @Override
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }



    @Bean
    public AuthenticationSuccessHandler mobileLoginSuccessHandler() {
        return MobileLoginSuccessHandler.builder()
                .objectMapper(objectMapper)
                .clientDetailsService(clientDetailsService)
                .passwordEncoder(passwordEncoder())
                .defaultAuthorizationServerTokenServices(defaultAuthorizationServerTokenServices).build();
    }

    @Bean
    public MobileSecurityConfigurer mobileSecurityConfigurer() {
        MobileSecurityConfigurer mobileSecurityConfigurer = new MobileSecurityConfigurer();
        mobileSecurityConfigurer.setMobileLoginSuccessHandler(mobileLoginSuccessHandler());
        mobileSecurityConfigurer.setUserDetailsService(userDetailsService);
        return mobileSecurityConfigurer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
