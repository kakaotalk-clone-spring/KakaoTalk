package com.example.KakaoTalk.common.handler

import com.example.KakaoTalk.common.exception.ErrorResponse
import com.example.KakaoTalk.common.response.BaseResponse
import com.example.KakaoTalk.common.response.BaseResponseStatus
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

class CustomAuthenticationEntryPoint(private val objectMapper: ObjectMapper) : AuthenticationEntryPoint {
//인가 실패 시 핸들러
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        if (response != null) {
            response.contentType = "text/plain;charset=UTF-8"
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.writer.write(objectMapper.writeValueAsString(BaseResponse<Unit>(BaseResponseStatus.AUTHENTICATION_ERROR)))
            response.writer.flush()
            response.writer.close()
        }
    }

}