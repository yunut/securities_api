package com.catches.securities_api.application.port.`in`

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto

interface BondUseCase {

    fun getBondSimpleInfo(name: String): BondSimpleDto?

}