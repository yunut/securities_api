package com.catches.securities_api.application.port.`in`

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto

interface UserUseCase {
    fun createUser(id: String)

    fun createUserBond(userId: String, bondId: String)

    fun getUserBondList(userId: String): List<BondSimpleDto>
}