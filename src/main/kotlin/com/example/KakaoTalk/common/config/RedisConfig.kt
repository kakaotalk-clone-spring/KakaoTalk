package com.example.KakaoTalk.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession

@Configuration
@EnableRedisHttpSession
class RedisConfig {
    @Value("\${spring.data.redis.host}")
    private lateinit var host : String

    @Value("\${spring.data.redis.port}")
    private lateinit var port: String

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()
        redisStandaloneConfiguration.hostName = host
        redisStandaloneConfiguration.port = port.toInt()
        return LettuceConnectionFactory(redisStandaloneConfiguration)
    }

}