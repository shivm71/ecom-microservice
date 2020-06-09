package com.example.rawredis.Config;

import com.example.rawredis.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class redisconfig{

    @Autowired
    private JedisConnectionFactory jedisConnFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public JacksonJsonRedisSerializer<Product> jacksonJsonRedisJsonSerializer() {
        JacksonJsonRedisSerializer<Product> jacksonJsonRedisJsonSerializer = new JacksonJsonRedisSerializer<>(Product.class);
        return jacksonJsonRedisJsonSerializer;
    }


    @Bean
    public RedisTemplate<String, Product> redisTemplate() {
        RedisTemplate<String, Product> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
        return redisTemplate;
    }


}
