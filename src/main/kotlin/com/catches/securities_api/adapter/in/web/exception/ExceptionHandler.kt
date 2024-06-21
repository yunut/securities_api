package com.catches.securities_api.adapter.`in`.web.exception

import com.catches.securities_api.adapter.`in`.web.response.MetaBody
import com.catches.securities_api.adapter.`in`.web.response.ResponseBody
import com.catches.securities_api.adapter.out.persistence.exception.NotFoundException
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleDefaultException(e: Exception, response: HttpServletResponse): ResponseEntity<ResponseBody> {
        return createResponse(WebErrors.UNKNOWN_ERROR, "서버 관리자에게 문의하세요")
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException, response: HttpServletResponse): ResponseEntity<ResponseBody> {
        return createResponse(WebErrors.BAD_REQUEST, e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ResponseBody> {
        val fieldError: FieldError? = e.bindingResult.fieldError
        return createResponse(WebErrors.BAD_REQUEST, fieldError?.defaultMessage)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingServletRequestParameterException(e: MissingServletRequestParameterException): ResponseEntity<ResponseBody> {
        return createResponse(WebErrors.INVALID_PARAMETER, "'${e.parameterName}' parameter is missing or null")
    }

    @ExceptionHandler(MissingRequestHeaderException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingRequestHeaderException(e: MissingRequestHeaderException): ResponseEntity<ResponseBody> {
        val body = ResponseBody(MetaBody(WebErrors.MISSING_HEADER.httpStatus.value(), e.message), null)
        return ResponseEntity(body, WebErrors.MISSING_HEADER.httpStatus)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ResponseBody> {
        val body = ResponseBody(MetaBody(WebErrors.BAD_REQUEST.httpStatus.value(), e.message ?: "No Message"), null)
        return ResponseEntity(body, WebErrors.BAD_REQUEST.httpStatus)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ResponseEntity<ResponseBody> {
        val body = ResponseBody(MetaBody(WebErrors.BAD_REQUEST.httpStatus.value(), e.message ?: "No Message"), null)
        return ResponseEntity(body, WebErrors.BAD_REQUEST.httpStatus)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ResponseBody> {
        val body = ResponseBody(MetaBody(WebErrors.BAD_REQUEST.httpStatus.value(), e.message ?: "No Message"), null)
        return ResponseEntity(body, WebErrors.BAD_REQUEST.httpStatus)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ResponseBody> {
        val body = ResponseBody(MetaBody(WebErrors.NOT_FOUND.httpStatus.value(), e.message ?: "유저 혹은 채권 정보가 존재하지 않습니다."), null)
        return ResponseEntity(body, WebErrors.NOT_FOUND.httpStatus)
    }


    private fun createResponse(error: WebErrors, message: String?): ResponseEntity<ResponseBody> {
        val body = ResponseBody(MetaBody(error.httpStatus.value(), message ?: "No Message"), null)
        return ResponseEntity(body, error.httpStatus)
    }
}