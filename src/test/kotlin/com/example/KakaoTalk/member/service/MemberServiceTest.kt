package com.example.KakaoTalk.member.service

import com.example.KakaoTalk.common.exception.BaseException
import com.example.KakaoTalk.common.util.LOGIN_USER
import com.example.KakaoTalk.member.domain.Member
import com.example.KakaoTalk.member.dto.LoginDto
import com.example.KakaoTalk.member.dto.RegisterDto
import com.example.KakaoTalk.member.repository.MemberRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockHttpSession
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest(properties = ["spring.config.location=classpath:application-test.yml"])
internal class MemberServiceTest {

    private val memberRepository = mock(MemberRepository::class.java)
    private val passwordEncoder = mock(PasswordEncoder::class.java)
    private val memberService = MemberService(memberRepository, passwordEncoder)

    @Test
    fun `로그인_성공`() {
        val loginDto = LoginDto("testUser", "password123")
        val member = Member(id = "testUser", password = "encodedPassword")

        `when`(memberRepository.findById("testUser")).thenReturn(java.util.Optional.of(member))
        `when`(passwordEncoder.matches(loginDto.password, member.password)).thenReturn(true)

        val session = MockHttpSession()
        val sessionId = memberService.login(session, loginDto)

        //반환 값 테스트
        assertEquals(session.id, sessionId)
        //세션 테스트
        assertEquals(member, session.getAttribute(LOGIN_USER))
    }

    @Test
    fun `로그인_실패_비밀번호`() {
        val loginDto = LoginDto("testUser", "wrongPassword")
        val member = Member(id = "testUser", password = "encodedPassword")

        `when`(memberRepository.findById("testUser")).thenReturn(java.util.Optional.of(member))
        `when`(passwordEncoder.matches(loginDto.password, member.password)).thenReturn(false)

        val session = MockHttpSession()

        assertThrows(BaseException::class.java) {
            memberService.login(session, loginDto)
        }
    }

    @Test
    fun `로그인_실패_미가입`() {
        val loginDto = LoginDto("nonExistingUser", "password123")

        `when`(memberRepository.findById("nonExistingUser")).thenReturn(java.util.Optional.empty())

        val session = MockHttpSession()

        assertThrows(BaseException::class.java) {
            memberService.login(session, loginDto)
        }
    }

    @Test
    fun `로그아웃_성공`() {
        val session = MockHttpSession()
        session.setAttribute(LOGIN_USER, Member(id = "testUser", password = "encodedPassword"))

        memberService.logout(session)

        assertNull(session.getAttribute(LOGIN_USER))
    }

    @Test
    fun `회원가입_성공`() {
        val registerDto = RegisterDto("newUser", "password123", "John Doe")
        `when`(passwordEncoder.encode(registerDto.password)).thenReturn("encodedPassword")
        memberService.register(registerDto)
        verify(memberRepository, times(1)).save(any(Member::class.java))
    }


}
