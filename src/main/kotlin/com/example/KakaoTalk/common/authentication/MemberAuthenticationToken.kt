package com.example.KakaoTalk.common.authentication

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class MemberAuthenticationToken (private val principal:String, private val credentials:String,
                                 authorities: MutableCollection<out GrantedAuthority>?
) : AbstractAuthenticationToken(authorities) {
    override fun getCredentials(): Any {
        return credentials
    }

    override fun getPrincipal(): Any {
        return principal
    }
}