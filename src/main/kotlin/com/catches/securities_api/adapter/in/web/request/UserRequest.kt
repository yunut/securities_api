package com.catches.securities_api.adapter.`in`.web.request

data class UserCreateRequest(
    val id: String
)

data class UserBondRCreateRequest(
    val userId: String,
    val bondId: String
)