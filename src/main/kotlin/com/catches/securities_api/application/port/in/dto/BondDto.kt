package com.catches.securities_api.application.port.`in`.dto

import java.math.BigDecimal
import java.time.LocalDate

data class BondSimpleDto(
    val bondName: String, // 채권명
    val surfaceInterestRate: BigDecimal, // 표면 이자율
    val issueDate: LocalDate, // 발행일
    val expiredDate: LocalDate, // 만기일
    val interestChange: String, // 금리 변동 구분
)