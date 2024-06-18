package com.catches.securities_api.adapter.`in`.web

import com.catches.securities_api.adapter.`in`.web.request.UserCreateRequest
import com.catches.securities_api.adapter.`in`.web.response.MetaBody
import com.catches.securities_api.adapter.`in`.web.response.ResponseBody
import com.catches.securities_api.application.port.`in`.UserUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    private val userUseCase: UserUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/create")
    fun createUser(
        @RequestBody user: UserCreateRequest,
    ): ResponseBody {

        userUseCase.createUser(user.id)
        return ResponseBody(
            meta = MetaBody(201, "User created"),
            data = null
        )
    }
}