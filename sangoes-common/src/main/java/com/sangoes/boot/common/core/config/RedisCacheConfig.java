package com.sangoes.boot.common.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

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
public class RedisCacheConfig {

//    /**
//     * redisTemplate
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(mapper);
//
//        template.setValueSerializer(serializer);
//        // 使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    /**
//     * redis缓存和EhCache缓存不能同时存在
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        config = config
//                // 设置缓存的默认过期时间，也是使用Duration设置
//                .prefixKeysWith(CommonConstants.COMMON_REDIS_PREFIX).entryTtl(Duration.ofDays(1))
//                // 不缓存空值
//                .disableCachingNullValues();
//        // 设置一个初始化的缓存空间set集合
////        Set<String> cacheNames = new HashSet<>();
////        cacheNames.add("common");
//        // 对每个缓存空间应用不同的配置
//        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//        configMap.put("common", config.entryTtl(Duration.ofSeconds(120)));
//        // RedisCacheManager
//        RedisCacheManager cacheManager =
//                RedisCacheManager.builder(redisConnectionFactory)
//                        // 使用自定义的缓存配置初始化一个cacheManager
////                        .initialCacheNames(cacheNames)
//                        // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                        .withInitialCacheConfigurations(configMap).build();
//        return cacheManager;
//    } /**
//     * redisTemplate
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//
//        // 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        serializer.setObjectMapper(mapper);
//
//        template.setValueSerializer(serializer);
//        // 使用StringRedisSerializer来序列化和反序列化redis的key值
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    /**
//     * redis缓存和EhCache缓存不能同时存在
//     *
//     * @param redisConnectionFactory
//     * @return
//     */
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
//        config = config
//                // 设置缓存的默认过期时间，也是使用Duration设置
//                .prefixKeysWith(CommonConstants.COMMON_REDIS_PREFIX).entryTtl(Duration.ofDays(1))
//                // 不缓存空值
//                .disableCachingNullValues();
//        // 设置一个初始化的缓存空间set集合
////        Set<String> cacheNames = new HashSet<>();
////        cacheNames.add("common");
//        // 对每个缓存空间应用不同的配置
//        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
//        configMap.put("common", config.entryTtl(Duration.ofSeconds(120)));
//        // RedisCacheManager
//        RedisCacheManager cacheManager =
//                RedisCacheManager.builder(redisConnectionFactory)
//                        // 使用自定义的缓存配置初始化一个cacheManager
////                        .initialCacheNames(cacheNames)
//                        // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                        .withInitialCacheConfigurations(configMap).build();
//        return cacheManager;
//    }

    @Value("${redis.cache.prefix:sangoes}")
    private String prefix;
    @Value("${redis.cache.expire:3600}")
    private Integer expire;

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
//        redisCacheConfigurationMap.put("permission", this.redisCacheConfiguration(30));

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
}
