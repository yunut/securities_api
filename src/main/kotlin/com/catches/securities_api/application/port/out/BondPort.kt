package com.catches.securities_api.application.port.out

import com.catches.securities_api.application.port.`in`.dto.BondRankInDto
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto

interface BondPort {

    fun getBondDetailInfo(code: String): BondSimpleDto?

    fun findBondList(name: String): List<BondSimpleDto>

    fun getBondListGroupByGrade(): List<BondRankInDto>
}