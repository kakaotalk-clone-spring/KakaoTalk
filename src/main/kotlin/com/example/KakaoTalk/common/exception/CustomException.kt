package com.example.KakaoTalk.common.exception

class CustomException(val errorCode: ErrorCode) : RuntimeException() {
}