package com.catches.securities_api.application.port.out

import com.catches.securities_api.application.port.`in`.dto.PosDto

interface PosPort {
    fun getPosSchedule(year: String): List<PosDto>
}