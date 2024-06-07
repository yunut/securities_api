package com.catches.securities_api.application.port.out.dto

data class BondRankOutDto(
    val grade: String,
    val bondList: List<BondRankBody>,
)

data class BondRankBody(
    val name: String,
    val surfaceInterestRate: Double,
    val grade: String,
    val expiredDate: String
)