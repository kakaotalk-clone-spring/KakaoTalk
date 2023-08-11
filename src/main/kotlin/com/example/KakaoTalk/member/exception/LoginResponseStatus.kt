package com.example.KakaoTalk.member.exception

import com.example.KakaoTalk.common.response.ResponseStatus
import org.springframework.http.HttpStatus

enum class LoginResponseStatus(
    override val isSuccess: Boolean,
    override val code: Int,
    override val message: String
) : ResponseStatus {

    ACCESS_ERROR(false, HttpStatus.LOCKED.value(), "접근 권한이 없습니다.")
    ,AUTHENTICATION_ERROR(false, HttpStatus.UNAUTHORIZED.value(), "인증 정보가 존재하지 않습니다.")

}