package com.example.KakaoTalk.chat.entity

import java.io.Serializable
import java.util.*

data class ChatRoom(val name: String) : Serializable {
    val id: String
        get() = UUID.randomUUID().toString()
}
