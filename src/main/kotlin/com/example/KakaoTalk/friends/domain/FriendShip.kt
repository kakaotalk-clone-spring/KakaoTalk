package com.example.KakaoTalk.friends.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "friendships")
data class FriendShip(

    val memberId: String,
    val friendId: String

)
