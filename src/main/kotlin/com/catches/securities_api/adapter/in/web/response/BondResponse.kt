package com.catches.securities_api.adapter.`in`.web.response

import java.math.BigDecimal
import java.time.LocalDate

data class BondResponse(
    val bondName: String, // 채권명
    val surfaceInterestRate: Double, // 표면 이자율
    val issueDate: String, // 발행일
    val expiredDate: String, // 만기일
    val interestChange: String, // 금리 변동 구분
)
