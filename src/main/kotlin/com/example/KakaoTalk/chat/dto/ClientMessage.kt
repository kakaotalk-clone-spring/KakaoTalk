package com.example.KakaoTalk.chat.dto

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter


@Getter
@NoArgsConstructor
@Setter
class ClientMessage {
    private val senderName: String = ""
    private val message: String = ""
}
