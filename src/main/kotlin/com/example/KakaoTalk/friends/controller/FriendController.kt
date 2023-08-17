package com.example.KakaoTalk.friends.controller

import com.example.KakaoTalk.common.response.BaseResponse
import com.example.KakaoTalk.common.util.LOGIN_USER
import com.example.KakaoTalk.friends.domain.Friend
import com.example.KakaoTalk.friends.service.FriendService
import com.example.KakaoTalk.member.domain.Member
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/friends")
class FriendController (
    private val friendService: FriendService
        ) {

    @PostMapping("/{friendId}")
    fun addFriend(httpSession: HttpSession, @PathVariable friendId: String) : BaseResponse<Unit>{
        val memberId = (httpSession.getAttribute(LOGIN_USER) as Member).id
        friendService.addFriend(memberId, friendId)
        return BaseResponse(Unit)
    }

    @GetMapping("")
    fun findAllFriends(httpSession: HttpSession) : BaseResponse<List<Friend>> {
        val memberId = (httpSession.getAttribute(LOGIN_USER) as Member).id
        val friends = friendService.findAllFriends(memberId)
        return BaseResponse(friends)
    }

}