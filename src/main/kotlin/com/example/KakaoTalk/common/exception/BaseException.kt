package com.example.KakaoTalk.common.exception

import com.example.KakaoTalk.common.response.ResponseStatus

class BaseException(val responseStatus: ResponseStatus) : RuntimeException() {
}