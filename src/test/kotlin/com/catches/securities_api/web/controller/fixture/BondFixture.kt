package com.catches.securities_api.web.controller.fixture

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import java.math.BigDecimal
import java.time.LocalDate

inline fun bondSimpleDtoBuild(block: BondSimpleDtoBuilder.() -> Unit = {}) =
    BondSimpleDtoBuilder().apply(block).build()

class BondSimpleDtoBuilder {
    var bondName: String = "Samsung76" // 채권명
    var surfaceInterestRate: BigDecimal = BigDecimal(0.08) // 표면 이자율
    var issuerName: String = "Samsung" // 발행기관
    var issueDate: LocalDate = LocalDate.now() // 발행일
    var expiredDate: LocalDate = LocalDate.now().minusMonths(12L) // 만기일
    var interestChange: String = "fixed" // 금리 변동 구분
    var interestType: String = "coupon" // 이자 유형
    var price: BigDecimal = BigDecimal(10000) // 종가
    var priceDate: String = "20240101" // 종가 일자

    fun build(): BondSimpleDto = BondSimpleDto(
        bondName = bondName,
        surfaceInterestRate = surfaceInterestRate,
        issuerName = issuerName,
        issueDate = issueDate,
        expiredDate = expiredDate,
        interestChange = interestChange,
        interestType = interestType,
        price = price,
        priceDate = priceDate,
    )
}