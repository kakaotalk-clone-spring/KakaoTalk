package com.example.KakaoTalk.member.domain

import com.example.KakaoTalk.common.authentication.MemberAuthenticationToken
import com.example.KakaoTalk.common.util.ROLE_USER
import com.example.KakaoTalk.member.dto.RegisterDto
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.StringTokenizer

@Document(collection = "member")
data class Member(

    @Id
    val id: String,
    val name: String,
    val password: String,
    val profile_img: String? = null,
    val friends: MutableList<String> = mutableListOf(),
    val roles: String,
    @CreatedDate
    val createdAt: LocalDateTime?,
    @LastModifiedDate
    var updatedAt: LocalDateTime?


) : java.io.Serializable {
    fun addFriend(friendId: String) {
        if (!friends.contains(friendId)) friends.add(friendId)
    }

    fun isPasswordMatch(passwordEncoder: PasswordEncoder, password: String): Boolean {
        return passwordEncoder.matches(password, this.password)
    }

    fun makeAuthentication(): MemberAuthenticationToken {
        val roles = ArrayList<GrantedAuthority>()
        val st = StringTokenizer(this.roles, ",")
        while(st.hasMoreTokens()){
            roles.add(SimpleGrantedAuthority(st.nextToken()))
        }
        return MemberAuthenticationToken(id, password, roles)
    }

    companion object {

        fun createNewMember(passwordEncoder:PasswordEncoder, registerDto: RegisterDto): Member {
            val encryptedPassword = passwordEncoder.encode(registerDto.password)
            return Member(
                id = registerDto.id,
                name = registerDto.name,
                password = encryptedPassword,
                profile_img = registerDto.profile_img,
                roles = ROLE_USER,
                createdAt = null,
                updatedAt = null
            )
        }
    }


}
