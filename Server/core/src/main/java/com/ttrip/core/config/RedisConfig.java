package com.ttrip.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.redis.port}")
    private String redisPort;
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    @Primary
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost);
        redisStandaloneConfiguration.setPort(Integer.parseInt(redisPort));
        redisStandaloneConfiguration.setPassword(password);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
    @Bean(name = "liveRedisTemplate")
    public RedisTemplate<String, Object> liveRedisTemplate(){
        RedisTemplate<String, Object> liveRedisTemplate = new RedisTemplate<>();
        liveRedisTemplate.setKeySerializer(new StringRedisSerializer());
        liveRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        liveRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        liveRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        liveRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return liveRedisTemplate;
    }
    // GeoSpartial 기능 구현
    @Bean(name = "socketRedisTemplate")
    public RedisTemplate<String, Object> socketRedisTemplate(){
        RedisTemplate<String, Object> socketRedisTemplate = new RedisTemplate<>();
//        socketRedisTemplate.setEnableTransactionSupport(true);
        socketRedisTemplate.setKeySerializer(new StringRedisSerializer());
        socketRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        socketRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return socketRedisTemplate;
    }

    @Bean(name = "surveyRedisTemplate")
    public RedisTemplate<String, Object> surveyRedisTemplate(){
        RedisTemplate<String, Object> surveyRedisTemplate = new RedisTemplate<>();
        surveyRedisTemplate.setKeySerializer(new StringRedisSerializer());
        surveyRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        surveyRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        surveyRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        surveyRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return surveyRedisTemplate;
    }

    @Bean(name = "matchRedisTemplate")
    public RedisTemplate<String, Object> matchRedisTemplate(){
        RedisTemplate<String, Object> matchRedisTemplate = new RedisTemplate<>();
        matchRedisTemplate.setKeySerializer(new StringRedisSerializer());
        matchRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        matchRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        matchRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        matchRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return matchRedisTemplate;
    }
}