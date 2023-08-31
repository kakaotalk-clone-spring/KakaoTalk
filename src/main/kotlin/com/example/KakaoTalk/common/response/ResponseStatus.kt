package com.example.KakaoTalk.common.response

interface ResponseStatus {
    val isSuccess: Boolean
    val code: Int
    val message: String
}