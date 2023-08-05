package com.example.KakaoTalk.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "message")
data class Message(

    @Indexed(unique = true)
    val id : String,
    val senderId : String,
    val receiverId : String,
    val roomId : String,
    val content : String,
    @CreatedDate
    var createdAt: LocalDateTime

    )
