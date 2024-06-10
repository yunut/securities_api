package com.catches.securities_api.application.port.`in`

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.dto.BondRankOutDto

interface BondUseCase {

    fun getBondSimpleInfo(name: String): BondSimpleDto?

    fun getBondListGroupByGrade(): List<BondRankOutDto>

}