package com.example.KakaoTalk.friends.service

import com.example.KakaoTalk.friends.domain.Friend
import com.example.KakaoTalk.friends.domain.FriendShip
import com.example.KakaoTalk.friends.repository.FriendRepository
import com.example.KakaoTalk.member.domain.Member
import com.example.KakaoTalk.member.repository.MemberRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*


@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
internal class FriendServiceTest {

    private val friendRepository = mock(FriendRepository::class.java)
    private val memberRepository = mock(MemberRepository::class.java)
    private val friendService = FriendService(friendRepository, memberRepository)

    @Test
    fun `친구_추가`() {
        //given
        val memberId = "member1"
        val friendId = "friend1"

        //when
        friendService.addFriend(memberId, friendId)

        //then
        verify(friendRepository, times(1)).save(FriendShip(memberId, friendId))
    }

    @Test
    fun `친구_목록_조회`() {
        //given
        val memberId = "member1"
        val friendId1 = "friend1"
        val friendId2 = "friend2"

        val friendShips = listOf(
            FriendShip(memberId, friendId1), FriendShip(memberId, friendId2)
        )

        val member1 = Member(id = friendId1, name = "friend1")
        val member2 = Member(id = friendId2, name = "friend2")

        val friend1 = Friend(id = member1.id, name = member1.name)
        val friend2 = Friend(id = member2.id, name = member2.name)

        //when
        `when`(friendRepository.findAllByMemberId(memberId)).thenReturn(friendShips)
        `when`(memberRepository.findById(friendId1)).thenReturn(Optional.of(member1))
        `when`(memberRepository.findById(friendId2)).thenReturn(Optional.of(member2))
        val friends = friendService.findAllFriends(memberId)

        //then
        assertEquals(2, friends.size)
        assertEquals(friend1, friends[0])
        assertEquals(friend2, friends[1])
    }


}
