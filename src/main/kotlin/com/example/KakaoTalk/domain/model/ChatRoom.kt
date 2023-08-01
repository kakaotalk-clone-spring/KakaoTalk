package com.example.KakaoTalk.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection="chatroom")
data class ChatRoom(

    @Indexed(unique = true)
    val id : String? = null,
    val name : String? = "",
    val members : MutableList<String> = mutableListOf(),
    @CreatedDate
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

)
