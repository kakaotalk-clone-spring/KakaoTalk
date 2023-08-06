package com.example.KakaoTalk.member.controller

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
    fun login(session: HttpSession, @RequestBody loginDto: LoginDto): ResponseEntity<String> {
        val sessionId = memberService.login(session, loginDto)
        return ResponseEntity.ok(sessionId)
    }

    @PostMapping("/register")
    fun register(@RequestBody registerDto: RegisterDto): ResponseEntity<String> {
        memberService.register(registerDto)
        return ResponseEntity.ok(registerDto.id)
    }

    @PostMapping("/logout")
    fun logout(session:HttpSession) : ResponseEntity<String> {
        memberService.logout(session)
        return ResponseEntity.ok().build()
    }

    @GetMapping("/test")
    fun test(session: HttpSession) : ResponseEntity<String>{
        //val maybeMember = session.getAttribute(LOGIN_USER)
        //return ResponseEntity.ok((maybeMember as Member).id)
        return ResponseEntity.ok("인가 성공")
    }

}