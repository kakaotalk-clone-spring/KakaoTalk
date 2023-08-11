package com.example.KakaoTalk.common.exception

import com.example.KakaoTalk.common.response.ResponseStatus

class CustomException(val errorCode: ResponseStatus) : RuntimeException() {
}