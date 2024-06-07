package com.catches.securities_api.application.port.`in`.dto

import java.math.BigDecimal
import java.time.LocalDate

data class PosDto(
    val posName: String,
    val posStartDate: LocalDate,
    val posEndDate: LocalDate,
    val posConfirmedPrice: Int? = null,
    val posDesiredMinPrice: Int,
    val posDesiredMaxPrice: Int,
    val posCompetitionRate: BigDecimal? = null,
    val posTakenCompany: String
)