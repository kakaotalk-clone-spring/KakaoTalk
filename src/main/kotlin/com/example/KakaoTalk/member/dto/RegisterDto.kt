package com.example.KakaoTalk.member.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RegisterDto (

    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("profile_img") val profile_img: String? = null

)
