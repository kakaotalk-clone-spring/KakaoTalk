package com.example.KakaoTalk.friends.service

import com.example.KakaoTalk.friends.domain.Friend
import com.example.KakaoTalk.friends.domain.FriendShip
import com.example.KakaoTalk.friends.repository.FriendRepository
import com.example.KakaoTalk.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class FriendService (
    private val friendRepository: FriendRepository
    ,private val memberRepository: MemberRepository
    ) {

    //친구 추가
    fun addFriend(memberId : String, friendId : String) {
        friendRepository.save(FriendShip(memberId, friendId))
    }

    //친구 목록 조회
    fun findAllFriends(memberId: String) : List<Friend> {

        val friendShips = friendRepository.findAllByMemberId(memberId)

        //우선 이렇게 구현 하지만 뭔가 찝찝 합니다... 조회 쿼리를 너무 많이 날리는 것 같아서.. 성능 개선이 필요할 수도 있을 듯!
        val friends = friendShips.map {
            val member = memberRepository.findById(it.friendId).get()
            Friend(member.id, member.name, member.profile_img, member.background_img)
        }

        return friends

    }

    //친구 상세 조회
    fun findFriend(friendId: String): Friend {
        val member = memberRepository.findById(friendId).get()
        return Friend(friendId, member.name, member.profile_img, member.background_img)
    }


}