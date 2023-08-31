package com.example.KakaoTalk.friends.repository

import com.example.KakaoTalk.friends.domain.FriendShip
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendRepository : MongoRepository<FriendShip, String> {
    fun findAllByMemberId(memberId : String) : List<FriendShip>
}