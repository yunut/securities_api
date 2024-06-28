package com.catches.securities_api.application

import com.catches.securities_api.application.enum.GradeGroup
import com.catches.securities_api.application.port.`in`.BondUseCase
import com.catches.securities_api.application.port.`in`.dto.BondPriceDetail
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.BondPort
import com.catches.securities_api.application.port.out.dto.BondRankBody
import com.catches.securities_api.application.port.out.dto.BondRankOutDto
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

@Service
class BondService(
    private val bondPort: BondPort,
): BondUseCase {

    override fun getBondDetailInfo(code: String): BondPriceDetail? {
        val currentDate = LocalDate.now()
        val bond = bondPort.getBondDetailInfo(code)!!

        return BondPriceDetail(
            bondId = bond.bondId,
            bondName = bond.bondName,
            surfaceInterestRate = bond.surfaceInterestRate,
            issuerName = bond.issuerName,
            issueDate = bond.issueDate,
            expiredDate = bond.expiredDate,
            interestPaymentCycle = bond.interestPaymentCycle,
            optionType = bond.optionType,
            securitiesItemKindName = bond.securitiesItemKindName,
            interestChange = bond.interestChange,
            interestType = bond.interestType,
            price = bond.price ?: 0.toBigDecimal(),
            priceDate = bond.priceDate,
            monthlyAveragePriceList = bond.priceHistoryList?.groupBy { YearMonth.from(it.pricedDate) }
                ?.map { (month, history) ->
                    val monthDifference = YearMonth.from(currentDate).until(month, ChronoUnit.MONTHS)

                    val averagePrice = history
                        .map { it.price }
                        .reduce { acc, price -> acc + price }
                        .divide(BigDecimal(history.size), RoundingMode.HALF_UP)

                    (-monthDifference).toString() to averagePrice
                }?.toMap()
        )
    }

    override fun searchBondList(name: String): List<BondSimpleDto> {
        return bondPort.findBondList(name)
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