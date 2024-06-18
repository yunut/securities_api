package com.catches.securities_api.application.port.`in`

import com.catches.securities_api.domain.user.User

interface UserUseCase {
    fun createUser(id: String)

    fun createUserBond(userId: String, bondId: String)
}