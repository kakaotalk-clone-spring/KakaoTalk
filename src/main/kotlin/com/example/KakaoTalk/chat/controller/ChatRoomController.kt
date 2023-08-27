package com.example.KakaoTalk.chat.controller

import com.example.KakaoTalk.chat.Service.ChatRoomService
import com.example.KakaoTalk.chat.dto.ChatDto
import com.example.KakaoTalk.chat.dto.ChatRoomDto
import com.example.KakaoTalk.chat.dto.toDto
import com.example.KakaoTalk.chat.entity.ChatRoom
import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*

@RestController
class ChatRoomController(
        private val chatRoomService: ChatRoomService
) {
    @MessageMapping("/pub/chat/room/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    fun message(@DestinationVariable roomId: String, chatDto: ChatDto): ChatDto {
        return chatDto
    }

    @PostMapping(
            value = ["/api/chat/room"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createRoom(@RequestParam name: String): ChatRoomDto {
        return chatRoomService.createRoom(name).toDto()
    }

    @GetMapping(
            value = ["/api/chat/rooms"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun findAllRoom(): List<ChatRoomDto> {
        return chatRoomService.findAllRoom().map { it.toDto() }
    }

    @GetMapping(
            value = ["/api/chat/room/{roomId}"],
            produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun roomInfo(@PathVariable roomId: String): ChatRoom {
        return chatRoomService.findById(roomId)
    }
}
