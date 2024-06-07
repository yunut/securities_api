package com.catches.securities_api.adapter.`in`.web.response

data class PosResponse(
    val posName: String,
    val posStartDate: String,
    val posEndDate: String,
    val posConfirmedPrice: Int? = null,
    val posDesiredMinPrice: Int,
    val posDesiredMaxPrice: Int,
    val posCompetitionRate: Double? = null,
    val posTakenCompany: String
)