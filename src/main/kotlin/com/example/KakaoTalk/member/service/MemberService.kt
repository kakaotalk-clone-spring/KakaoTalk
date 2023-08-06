package com.example.KakaoTalk.member.service

import com.example.KakaoTalk.common.exception.CustomException
import com.example.KakaoTalk.common.exception.ErrorCode
import com.example.KakaoTalk.common.util.LOGIN_USER
import com.example.KakaoTalk.member.repository.MemberRepository
import com.example.KakaoTalk.member.domain.Member
import com.example.KakaoTalk.member.dto.LoginDto
import com.example.KakaoTalk.member.dto.RegisterDto
import jakarta.servlet.http.HttpSession
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService (private val memberRepo: MemberRepository, private val passwordEncoder: PasswordEncoder) {

    fun login(session: HttpSession, loginDto: LoginDto) : String {
        //회원 정보 가져오기
        val member = memberRepo.findById(loginDto.id)
            .orElseThrow { CustomException(ErrorCode.LOGIN_DENIED) }
        //비밀번호 검증
        if(!member.isPasswordMatch(passwordEncoder, loginDto.password)) {
            throw CustomException(ErrorCode.LOGIN_DENIED)
        }
        session.setAttribute(LOGIN_USER, member)
        return session.id
    }

    fun logout(session:HttpSession){
        session.removeAttribute(LOGIN_USER)
    }

    fun register(registerDto: RegisterDto) {
        val member = Member.createNewMember(passwordEncoder, registerDto)
        memberRepo.save(member)
    }

    fun addFriendToUser(memberId: String, friendId: String) : Member? {
        val member = memberRepo.findById(memberId).orElse(null)
        if (member != null) {
            member.addFriend(friendId)
            memberRepo.save(member)
        }
        return member
    }

}