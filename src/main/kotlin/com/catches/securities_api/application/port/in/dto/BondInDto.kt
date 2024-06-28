package com.catches.securities_api.application.port.`in`.dto

import com.catches.securities_api.domain.bond.BondPriceHistory
import java.math.BigDecimal
import java.time.LocalDate

data class BondSimpleDto(
    val bondId: String, // 채권 ID
    val bondName: String, // 채권명
    val surfaceInterestRate: BigDecimal, // 표면 이자율
    val issuerName: String, // 발행기관
    val issueDate: String, // 발행일
    val expiredDate: String, // 만기일
    val interestPaymentCycle: String, // 이자 지급 주기
    val optionType: String? = null, // 옵션 타입 ex) call, put
    val securitiesItemKindName: String,
    val interestChange: String, // 금리 변동 구분
    val interestType: String, // 이자 유형
    val price: BigDecimal, // 종가
    val priceDate: String, // 종가 일자
    val priceHistoryList: List<BondPriceHistory>? = null
)

data class BondPriceDetail(
    val bondId: String, // 채권 ID
    val bondName: String, // 채권명
    val surfaceInterestRate: BigDecimal, // 표면 이자율
    val issuerName: String, // 발행기관
    val issueDate: String, // 발행일
    val expiredDate: String, // 만기일
    val interestPaymentCycle: String, // 이자 지급 주기
    val optionType: String? = null, // 옵션 타입 ex) call, put
    val securitiesItemKindName: String,
    val interestChange: String, // 금리 변동 구분
    val interestType: String, // 이자 유형
    val price: BigDecimal, // 종가
    val priceDate: String, // 종가 일자
    val monthlyAveragePriceList: Map<String, BigDecimal>? = null
)

data class BondRankInDto(
    val bondName: String,
    val surfaceInterestRate: BigDecimal,
    val expiredDate: LocalDate,
    val grade: String,
    val rank: Int,
)