package com.catches.securities_api.application.port.`in`

import com.catches.securities_api.application.port.`in`.dto.PosDto

interface PosUseCase {

    fun getPosSchedule(year: String): List<PosDto>
}