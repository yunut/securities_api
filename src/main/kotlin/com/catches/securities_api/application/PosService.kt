package com.catches.securities_api.application

import com.catches.securities_api.application.port.`in`.PosUseCase
import com.catches.securities_api.application.port.`in`.dto.PosDto
import com.catches.securities_api.application.port.out.PosPort
import org.springframework.stereotype.Service

@Service
class PosService(
    private val posPort: PosPort,
): PosUseCase {

    override fun getPosSchedule(year: String): List<PosDto> {
        return posPort.getPosSchedule(year)
    }
}