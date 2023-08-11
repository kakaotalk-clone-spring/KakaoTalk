package com.example.KakaoTalk.member.controller

import com.example.KakaoTalk.common.response.BaseResponse
import com.example.KakaoTalk.member.dto.LoginDto
import com.example.KakaoTalk.member.dto.RegisterDto
import com.example.KakaoTalk.member.service.MemberService
import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {

    @PostMapping("/login")
    fun login(session: HttpSession, @RequestBody loginDto: LoginDto): BaseResponse<String> {
        val sessionId = memberService.login(session, loginDto)
        return BaseResponse(sessionId)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerDto: RegisterDto): BaseResponse<String> {
        memberService.register(registerDto)
        return BaseResponse(registerDto.id)
    }

    @PostMapping("/logout")
    fun logout(session:HttpSession) : BaseResponse<Unit> {
        memberService.logout(session)
        return BaseResponse(Unit)
    }

    @GetMapping("/test")
    fun test(session: HttpSession) : BaseResponse<String>{
        //val maybeMember = session.getAttribute(LOGIN_USER)
        //return ResponseEntity.ok((maybeMember as Member).id)
        return BaseResponse("인가 성공")
    }

}