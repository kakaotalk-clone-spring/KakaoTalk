package com.example.KakaoTalk.friends.domain

data class Friend(

    val id : String,
    val name : String,
    val profile_img : String? = null,
    val background_img : String? = null,

)
