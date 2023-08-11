package com.example.KakaoTalk.common.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


class BaseResponse<T>(

    @JsonProperty("isSuccess")
    val isSuccess:Boolean,
    @JsonProperty("code")
    val code:Int,
    @JsonProperty("message")
    val message:String,
    @JsonProperty("result")
    val result:T?

) {
    //요청에 성공한 경우
    constructor(result: T) : this(
        BaseResponseStatus.SUCCESS.isSuccess,
        BaseResponseStatus.SUCCESS.code,
        BaseResponseStatus.SUCCESS.message,
        result
    )

    //요청에 실패한 경우
    constructor(status: ResponseStatus) : this(
        status.isSuccess,
        status.code,
        status.message,
        null
    )

}