package com.example.KakaoTalk.common.handler
import com.example.KakaoTalk.common.response.BaseResponse
import com.example.KakaoTalk.common.response.BaseResponseStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    private fun handleException(e: Exception): BaseResponse<Unit> {
        return handleExceptionInternal(BaseResponseStatus.SERVER_ERROR)
    }

    private fun handleExceptionInternal(status: BaseResponseStatus): BaseResponse<Unit> {
        return BaseResponse(status)
    }

}