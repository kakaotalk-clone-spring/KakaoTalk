package com.example.KakaoTalk.chat.controller

import com.example.KakaoTalk.chat.dto.ClientMessage
import lombok.RequiredArgsConstructor
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class ChatMessageController {


    /**
     * /pub/chat/room 으로 오는 메시지 반환
     */
    @MessageMapping("/chat/message")
    fun message(message: ClientMessage) {

    }
}
