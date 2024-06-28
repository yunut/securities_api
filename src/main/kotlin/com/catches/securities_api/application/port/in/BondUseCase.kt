package com.catches.securities_api.application.port.`in`

import com.catches.securities_api.application.port.`in`.dto.BondPriceDetail
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.dto.BondRankOutDto

interface BondUseCase {

    fun getBondDetailInfo(code: String): BondPriceDetail?

    fun searchBondList(name: String): List<BondSimpleDto>

    fun getBondListGroupByGrade(): List<BondRankOutDto>

}