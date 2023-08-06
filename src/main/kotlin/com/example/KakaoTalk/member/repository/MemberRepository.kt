package com.example.KakaoTalk.member.repository

import com.example.KakaoTalk.member.domain.Member
import org.springframework.data.mongodb.repository.MongoRepository

interface MemberRepository : MongoRepository<Member, String> {

}