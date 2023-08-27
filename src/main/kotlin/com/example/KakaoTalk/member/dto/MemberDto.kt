package com.example.KakaoTalk.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

data class MemberDto(

    @JsonProperty("id") val id: String = "",
    @JsonProperty("name") val name: String = "",
    @JsonProperty("profile_img") val profile_img: String? = null,
    @JsonProperty("background_img") val background_img: String? = null

)
