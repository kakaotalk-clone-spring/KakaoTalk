package com.example.KakaoTalk.domain.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "member")
data class Member(

    @Id
    val id : String? ="",
    val name : String? = "",
    val password : String? = "",
    val profile_img : String,
    val friends : MutableList<String> = mutableListOf(),
    @CreatedDate
    var createdAt: LocalDateTime? = null,
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null

) {
    fun addFriend(friendId: String) {
        if(!friends.contains(friendId)) friends.add(friendId)
    }
}