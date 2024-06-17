package com.catches.securities_api.application.port.`in`

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.dto.BondRankOutDto

interface UserUseCase {

    fun putUserBondHistory(name: String): BondSimpleDto?

}