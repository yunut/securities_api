package com.catches.securities_api.application.port.`in`.dto

import java.math.BigDecimal
import java.time.LocalDate

data class BondSimpleDto(
    val bondName: String, // 채권명
    val surfaceInterestRate: BigDecimal, // 표면 이자율
    val issuerName: String, // 발행기관
    val issueDate: LocalDate, // 발행일
    val expiredDate: LocalDate, // 만기일
    val interestChange: String, // 금리 변동 구분
    val interestType: String, // 이자 유형
    val price: BigDecimal, // 종가
    val priceDate: String, // 종가 일자
)

data class BondRankInDto(
    val bondName: String,
    val surfaceInterestRate: BigDecimal,
    val expiredDate: LocalDate,
    val grade: String,
    val rank: Int,
)