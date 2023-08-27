package com.example.KakaoTalk.chat.dto

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

data class ChatDto(
        val type: Type,
        val sender: String,
        val message: String
) {
    val createdAt: Long = LocalDateTime.now().toEpochMillis()

    enum class Type {
        ENTER, CHAT, EXIT
    }
}

private fun LocalDateTime.toEpochMillis(zoneId: ZoneId = ZoneOffset.UTC): Long = this.atZone(zoneId).toInstant().toEpochMilli()
