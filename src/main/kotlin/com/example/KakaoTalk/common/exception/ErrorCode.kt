package com.example.KakaoTalk.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode (val httpStatus: HttpStatus, val message:String){
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 예상치 못한 에러가 발생했습니다."),
    LOGIN_DENIED(HttpStatus.UNAUTHORIZED, "ID 혹은 비밀번호가 일치하지 않습니다.")
}