package com.example.KakaoTalk.chat.dto

import com.example.KakaoTalk.chat.enums.ClientMessageType
import lombok.AllArgsConstructor
import lombok.Getter

class ChatDto {

    @Getter
    @AllArgsConstructor
    class SendChatDto(
            val type: ClientMessageType,
            val channelId: String,
            val message: String,
            val userId: String
    )
}
