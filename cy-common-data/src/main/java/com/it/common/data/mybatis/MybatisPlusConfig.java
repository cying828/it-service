package com.it.common.data.mybatis;

import com.it.common.api.feign.RemoteUserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author Cying
 * @Date 2022/7/4 10:52
 * @Description
 */
@Configuration
//@ConditionalOnBean(DataSource.class)
@MapperScan("com.it.**.mapper")
public class MybatisPlusConfig {

    /**
     * 数据权限插件
     *
     * @param dataSource 数据源
     * @return DataScopeInterceptor
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public DataScopeInterceptor dataScopeInterceptor(DataSource dataSource) {
//        return new DataScopeInterceptor(dataSource);
//    }

    /**
     * 逻辑删除插件
     *
     * @return LogicSqlInjector
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }

    /**
     * 自动添加创建人等元数据插件
     * @return MetaInterceptor
     */
//    @Bean
//    @ConditionalOnMissingBean
//    public MetaInterceptor metaInterceptor() {
//        return new MetaInterceptor();
//    }
    /**
     * 项目权限插件
     * @return ProjectInterceptor
     */
//    @Bean
//    @ConditionalOnMissingBean(RemoteUserService.class)
//    public ProjectScopeInterceptor projectScopeInterceptor(RemoteUserService remoteUserService) {
//        return new ProjectScopeInterceptor(remoteUserService);
//    }


}
