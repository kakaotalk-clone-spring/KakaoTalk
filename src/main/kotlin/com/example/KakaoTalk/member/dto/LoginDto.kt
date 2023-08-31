package com.example.KakaoTalk.member.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class LoginDto(

    @JsonProperty("id") val id: String,
    @JsonProperty("password") val password: String

)
