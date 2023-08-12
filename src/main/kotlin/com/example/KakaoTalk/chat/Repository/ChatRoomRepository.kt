package com.example.KakaoTalk.chat.Repository

import com.example.KakaoTalk.chat.entity.ChatRoom
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Repository

@Repository
class ChatRoomRepository(private val redisTemplate: RedisTemplate<String, ChatRoom>) {
    companion object {
        const val CHAT_ROOMS = "chat-rooms:"
    }

    private val valueOps: ValueOperations<String, ChatRoom> = redisTemplate.opsForValue()

    fun findAllRoom(): List<ChatRoom> {
        val keys = redisTemplate.keys("$CHAT_ROOMS*")
        return keys.mapNotNull { key -> valueOps.get(key) }
    }

    fun findById(id: String): ChatRoom? {
        return valueOps.get("$CHAT_ROOMS$id")
    }

    fun createChatRoom(name: String): ChatRoom {
        val chatRoom = ChatRoom(name)
        valueOps.set("$CHAT_ROOMS${chatRoom.id}", chatRoom)
        return chatRoom
    }
}
