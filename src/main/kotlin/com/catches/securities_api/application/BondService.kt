package com.catches.securities_api.application

import com.catches.securities_api.application.enum.GradeGroup
import com.catches.securities_api.application.port.`in`.BondUseCase
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.BondPort
import com.catches.securities_api.application.port.out.dto.BondRankBody
import com.catches.securities_api.application.port.out.dto.BondRankOutDto
import org.springframework.stereotype.Service

@Service
class BondService(
    private val bondPort: BondPort,
): BondUseCase {

    override fun getBondSimpleInfo(name: String): BondSimpleDto? {
        return bondPort.getBondSimpleInfo(name)
    }

    override fun getBondListGroupByGrade(): List<BondRankOutDto> {
        val data = bondPort.getBondListGroupByGrade()

        return data.groupBy { GradeGroup.fromGrade(it.grade) }.toSortedMap().map {
            BondRankOutDto(
                grade = GradeGroup.convertGradeRange(it.key),
                bondList = it.value.map { bond ->
                    BondRankBody(
                        name = bond.bondName,
                        surfaceInterestRate = bond.surfaceInterestRate.toDouble(),
                        grade = bond.grade,
                        expiredDate = bond.expiredDate.toString()
                    )
                }.sortedByDescending { it.surfaceInterestRate }.take(3)
            )
        }
    }
}