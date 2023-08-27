package com.example.KakaoTalk.chat.Service

import com.example.KakaoTalk.chat.Repository.ChatRoomRepository
import com.example.KakaoTalk.chat.entity.ChatRoom
import org.springframework.stereotype.Service

@Service
class ChatRoomService(
        private val chatRoomRepository: ChatRoomRepository
) {

    fun findAllRoom(): List<ChatRoom> {
        return chatRoomRepository.findAllRoom()
    }

    fun findById(id: String): ChatRoom {
        var chatRoom = chatRoomRepository.findById(id)
                ?: throw IllegalArgumentException("존재하지 않는 채팅방입니다.")
        return chatRoom
    }

    fun createRoom(name: String): ChatRoom {
        return chatRoomRepository.createChatRoom(name)
    }
}
