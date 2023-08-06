package com.example.KakaoTalk.common.filter

import com.example.KakaoTalk.common.util.LOGIN_USER
import com.example.KakaoTalk.member.domain.Member
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class MemberAuthenticationFilter : OncePerRequestFilter() {
    //세션 인증 필터
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val member = request.session.getAttribute(LOGIN_USER) as? Member
        if(member!=null){
            SecurityContextHolder.getContext().authentication = member.makeAuthentication()
        }

        filterChain.doFilter(request, response)

    }
}