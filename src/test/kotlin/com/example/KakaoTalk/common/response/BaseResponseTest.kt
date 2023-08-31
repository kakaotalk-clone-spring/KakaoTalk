package com.example.KakaoTalk.common.response

import com.example.KakaoTalk.common.response.BaseResponseStatus.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BaseResponseTest {

    @Test
    @DisplayName("성공 결과 반환")
    fun responseSuccessTest(){

        //given
        val unit = Unit

        //when
        val response = BaseResponse(unit)

        //then
        assertEquals(response.isSuccess, SUCCESS.isSuccess)
        assertEquals(response.code, SUCCESS.code)
        assertEquals(response.message, SUCCESS.message)
        assertEquals(response.result, unit)

    }

    @Test
    @DisplayName("실패 결과 반환")
    fun responseFailureTest(){

        //given
        val error = SERVER_ERROR

        //when
        val response = BaseResponse<Unit>(error)

        //then
        assertEquals(response.isSuccess, error.isSuccess)
        assertEquals(response.code, error.code)
        assertEquals(response.message, error.message)
        assertEquals(response.result, null)

    }
}