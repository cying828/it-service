package com.it.common.data.cache;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author Cying
 * @Date 2022/7/2 10:51
 * @Description
 */
@EnableCaching
@Configuration
//@ConditionalOnBean(RedisConnectionFactory.class)
@AllArgsConstructor
public class RedisTemplateConfig {

    private final RedisConnectionFactory redisConnectionFactory;
    @Bean
    @Primary
    public RedisTemplate<String, Object> cyRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // key的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // value的序列化采用JdkSerializationRedisSerializer
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
