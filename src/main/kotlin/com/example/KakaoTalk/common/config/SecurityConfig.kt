package com.example.KakaoTalk.common.config

import com.example.KakaoTalk.common.filter.MemberAuthenticationFilter
import com.example.KakaoTalk.common.handler.CustomAccessDeniedHandler
import com.example.KakaoTalk.common.handler.CustomAuthenticationEntryPoint
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun corsConfigurationSource():CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowCredentials = true;
        configuration.addAllowedOrigin("*")
        configuration.addAllowedOriginPattern("*")
        configuration.addAllowedMethod("HEAD")
        configuration.addAllowedMethod("GET")
        configuration.addAllowedMethod("POST")
        configuration.addAllowedMethod("PUT")
        configuration.addAllowedMethod("PATCH")
        configuration.addAllowedMethod("DELETE")
        configuration.addAllowedHeader("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }

    @Bean
    fun userAuthenticationFilter() : MemberAuthenticationFilter {
        return MemberAuthenticationFilter()
    }

    @Bean
    fun authenticationEntryPoint(objectMapper:ObjectMapper):AuthenticationEntryPoint {
        return CustomAuthenticationEntryPoint(objectMapper)
    }

    @Bean
    fun accessDeniedHandler(objectMapper: ObjectMapper):AccessDeniedHandler {
        return CustomAccessDeniedHandler(objectMapper)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            //Request 에 대한 접근 설정
            .authorizeHttpRequests {
                it
                    .requestMatchers("/member/login", "/member/register")
                    .permitAll()
                    .anyRequest().hasAnyRole("USER")
            }

            // 사용 하지 않는 필터
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .headers { it.disable() }
            .httpBasic { it.disable() }
            .rememberMe { it.disable() }
            .logout { it.disable() }

            // 세션 설정
            .sessionManagement{
                it
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }

            // 인증/인가 실패 Response
            .exceptionHandling {
                it
                    .authenticationEntryPoint(authenticationEntryPoint(objectMapper()))
                    .accessDeniedHandler(accessDeniedHandler(objectMapper()))
            }

            //레디스 세션 설정
            .addFilterBefore(userAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter::class.java)

            //cors 설정
            .cors{
                it.configurationSource(corsConfigurationSource())
            }
        return http.build()
    }

}