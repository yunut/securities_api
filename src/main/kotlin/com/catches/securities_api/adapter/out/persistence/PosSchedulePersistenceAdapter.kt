package com.catches.securities_api.adapter.out.persistence

import com.catches.securities_api.adapter.out.persistence.repository.PosScheduleRepository
import com.catches.securities_api.application.port.`in`.dto.PosDto
import com.catches.securities_api.application.port.out.PosPort
import org.springframework.stereotype.Component

@Component
class PosSchedulePersistenceAdapter(
    private val posScheduleRepository: PosScheduleRepository
): PosPort {
    override fun getPosSchedule(year: String): List<PosDto> {
        return posScheduleRepository.findByPosStartDateYear(year.toInt()).map {
            PosDto(
                posName = it.posName,
                posStartDate = it.posStartDate,
                posEndDate = it.posEndDate,
                posConfirmedPrice = it.posConfirmedPrice,
                posDesiredMinPrice = it.posDesiredMinPrice,
                posDesiredMaxPrice = it.posDesiredMaxPrice,
                posCompetitionRate = it.posCompetitionRate,
                posTakenCompany = it.posTakenCompany
            )
        }
    }
}