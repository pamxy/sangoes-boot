package com.sangoes.boot.common.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangoes.boot.common.constants.TTLConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (c) 2018
 * Redis增强配置
 *
 * @author jerrychir
 * @date 2018/10/29 9:47 PM
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisCacheConfig {

    @Value("${redis.cache.prefix:sangoes}")
    private String prefix;
    @Value("${redis.cache.expire:3600}")
    private Integer expire;

    @Autowired
    private RedisTTLConfig redisTTLConfig;

    @PostConstruct
    public void loadDefaultTTLConfig() {
        Map<String, String> ttl = new HashMap<>();
        ttl.put(TTLConstants.LISTS, String.valueOf(TTLConstants.TTL_DAY));
        ttl.put(TTLConstants.PAGES, String.valueOf(TTLConstants.TTL_DAY));
        ttl.put(TTLConstants.INFOS, String.valueOf(TTLConstants.TTL_DAY));
        redisTTLConfig.getTtl().putAll(ttl);
    }


    /**
     * 增强redis
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                // 默认策略，未配置的 key 会使用这个
                this.redisCacheConfiguration(expire),
                // 指定 key 策略
                this.getRedisCacheConfigurationMap()
        );
    }

    /**
     * 设置过期项目
     *
     * @return
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        redisTTLConfig.ttl.forEach((k, v) -> redisCacheConfigurationMap.put(k, this.redisCacheConfiguration(Integer.valueOf(v))));
        return redisCacheConfigurationMap;
    }

    /***
     *  得到过期时间配置
     * @param seconds
     * @return
     */
    private RedisCacheConfiguration redisCacheConfiguration(Integer seconds) {
        // jackson2JsonRedisSerializer
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // redisCacheConfiguration
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration
                // prefix
                .prefixKeysWith(prefix + ":")
                // 序列化
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(jackson2JsonRedisSerializer)
                )
                // 设置过期时间
                .entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

//    /**
//     * 限流
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Serializable> limitRedisTemplate(LettuceConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
}
