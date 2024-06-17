package com.catches.securities_api.application

import com.catches.securities_api.application.port.`in`.UserUseCase
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.UserPort
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userPort: UserPort,
): UserUseCase {

    override fun putUserBondHistory(name: String): BondSimpleDto? {
        TODO("Not yet implemented")
    }
}