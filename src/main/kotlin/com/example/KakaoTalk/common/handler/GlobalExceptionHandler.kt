package com.example.KakaoTalk.common.handler
import com.example.KakaoTalk.common.exception.BaseException
import com.example.KakaoTalk.common.response.BaseResponse
import com.example.KakaoTalk.common.response.BaseResponseStatus
import com.example.KakaoTalk.common.response.ResponseStatus
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(BaseException::class)
    private fun handleBaseException(e: BaseException): BaseResponse<Unit>{
        return handleExceptionInternal(e.responseStatus, e)
    }

    @ExceptionHandler(Exception::class)
    private fun handleException(e: Exception): BaseResponse<Unit> {
        return handleExceptionInternal(BaseResponseStatus.SERVER_ERROR, e)
    }

    private fun handleExceptionInternal(status: ResponseStatus, e : Exception): BaseResponse<Unit> {
        logger.error(e.stackTraceToString())
        return BaseResponse(status)
    }

}