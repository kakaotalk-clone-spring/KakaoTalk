package com.example.KakaoTalk.member.controller

import com.example.KakaoTalk.common.response.BaseResponse
import com.example.KakaoTalk.member.dto.LoginDto
import com.example.KakaoTalk.member.dto.MemberDto
import com.example.KakaoTalk.member.dto.RegisterDto
import com.example.KakaoTalk.member.repository.MemberRepository
import com.example.KakaoTalk.member.service.MemberService
import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/member")
class MemberController(
    private val memberService: MemberService
    , private val memberRepository: MemberRepository
    ) {

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

    @GetMapping("/{member_id}")
    fun getMember(@PathVariable member_id: String) : BaseResponse<MemberDto> {
        val member = memberRepository.findById(member_id).get()
        val memberDto = MemberDto(member.id, member.name, member.profile_img, member.background_img)
        return BaseResponse(memberDto)
    }

}