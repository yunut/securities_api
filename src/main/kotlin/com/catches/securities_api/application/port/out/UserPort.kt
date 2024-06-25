package com.catches.securities_api.application.port.out

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.domain.user.User

interface UserPort {
    fun saveUser(id: String): User

    fun saveUserBond(userId: String, bondId: String)

    fun getUserBondList(userId: String): List<BondSimpleDto>
}