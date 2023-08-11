package com.example.KakaoTalk.common.handler

import com.example.KakaoTalk.common.exception.ErrorResponse
import com.example.KakaoTalk.common.response.BaseResponse
import com.example.KakaoTalk.common.response.BaseResponseStatus
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

class CustomAccessDeniedHandler (private val objectMapper: ObjectMapper) : AccessDeniedHandler {
//권한 없을 시 핸들러
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        if (response != null) {
            response.contentType = "test/plain;charset=UTF-8"
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.writer.write(objectMapper.writeValueAsString(BaseResponse<Unit>(BaseResponseStatus.ACCESS_ERROR)))
            response.writer.flush()
            response.writer.close()
        }
    }
}