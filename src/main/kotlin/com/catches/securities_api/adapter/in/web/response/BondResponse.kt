package com.catches.securities_api.adapter.`in`.web.response

data class BondResponse(
    val bondId: String, // 채권 ID
    val bondName: String, // 채권명
    val surfaceInterestRate: Double, // 표면 이자율
    val issuerName: String, // 발행기관
    val issueDate: String, // 발행일
    val expiredDate: String, // 만기일
    val interestChange: String, // 금리 변동 구분
    val interestType: String, // 이자 유형
    val price: Int, // 종가
    val priceDate: String, // 종가 일자
)

data class BondRankResponse(
    val grade: String,
    val bondList: List<BondRankResponseBody>,
)

data class BondRankResponseBody(
    val name: String,
    val surfaceInterestRate: Double,
    val expiredDate: String,
    val grade: String
)
