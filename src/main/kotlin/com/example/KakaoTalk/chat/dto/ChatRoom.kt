package com.example.KakaoTalk.chat.dto

import com.example.KakaoTalk.chat.entity.ChatRoom

data class ChatRoomDto(
        val id: String
)

internal fun ChatRoom.toDto() = ChatRoomDto(
        id = id
)
