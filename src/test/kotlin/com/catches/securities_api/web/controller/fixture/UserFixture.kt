package com.catches.securities_api.web.controller.fixture

import com.catches.securities_api.adapter.`in`.web.request.UserCreateRequest

inline fun userCreateRequestBuild(block: UserCreateRequestBuilder.() -> Unit = {}) =
    UserCreateRequestBuilder().apply(block).build()


class UserCreateRequestBuilder {
    var id: String = "test"

    fun build(): UserCreateRequest = UserCreateRequest(
        id = id
    )
}