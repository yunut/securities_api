package com.catches.securities_api.controller.fixture

import com.catches.securities_api.application.port.`in`.dto.PosDto
import com.fasterxml.jackson.annotation.JsonFormat
import java.math.BigDecimal
import java.time.LocalDate

inline fun basePosDtoBuild(block: BasePosDtoBuilder.() -> Unit = {}) =
    BasePosDtoBuilder().apply(block).build()

class BasePosDtoBuilder {
    var posName: String = "공모주 이름 테스트"
    @JsonFormat(pattern = "yyyy-MM-dd")
    var posStartDate: LocalDate = LocalDate.now()
    @JsonFormat(pattern = "yyyy-MM-dd")
    var posEndDate: LocalDate = LocalDate.now().minusDays(1L)
    var posConfirmedPrice: Int? = null
    var posDesiredMinPrice: Int = 900
    var posDesiredMaxPrice: Int = 1000
    var posCompetitionRate: BigDecimal = BigDecimal(1.1)
    var posTakenCompany: String = "테스트 회사"

    fun build(): PosDto = PosDto(
        posName = posName,
        posStartDate = posStartDate,
        posEndDate = posEndDate,
        posConfirmedPrice = posConfirmedPrice,
        posDesiredMinPrice = posDesiredMinPrice,
        posDesiredMaxPrice = posDesiredMaxPrice,
        posCompetitionRate = posCompetitionRate,
        posTakenCompany = posTakenCompany
    )
}