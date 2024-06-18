package com.catches.securities_api.application

import com.catches.securities_api.application.port.`in`.UserUseCase
import com.catches.securities_api.application.port.out.UserPort
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userPort: UserPort
): UserUseCase {
    override fun createUser(id: String) {
        userPort.saveUser(id)
    }

    override fun createUserBond(userId: String, bondId: String) {
        userPort.saveUserBond(userId, bondId)
    }
}