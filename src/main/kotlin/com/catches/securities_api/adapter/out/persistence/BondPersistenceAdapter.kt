package com.catches.securities_api.adapter.out.persistence

import com.catches.securities_api.adapter.out.persistence.repository.BondGradeRankRepository
import com.catches.securities_api.adapter.out.persistence.repository.BondPriceHistoryRepository
import com.catches.securities_api.adapter.out.persistence.repository.BondRepository
import com.catches.securities_api.application.port.`in`.dto.BondRankInDto
import com.catches.securities_api.application.port.out.BondPort
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import org.springframework.stereotype.Component
import java.lang.StringBuilder

@Component
class BondPersistenceAdapter(
    private val bondRepository: BondRepository,
    private val bondGradeRankRepository: BondGradeRankRepository,
    private val bondPriceHistoryRepository: BondPriceHistoryRepository
) : BondPort {

    override fun getBondDetailInfo(code: String): BondSimpleDto? {
        val bond = bondRepository.findByIsinCode(code)
        val bondPriceHistoryList = bondPriceHistoryRepository.findAllByCode(code)

        return bond?.let {
            BondSimpleDto(
                bondId = it.isinCode,
                bondName = it.isinCodeName,
                surfaceInterestRate = it.surfaceInterestRate,
                issuerName = it.issuer.name,
                issueDate = it.issueDate.toString(),
                expiredDate = it.expiredDate.toString(),
                interestPaymentCycle = it.interestPaymentCycle,
                optionType = it.optionType?.name,
                securitiesItemKindName = it.securitiesItemKind.name,
                interestChange = it.interestChange.name,
                interestType = it.interestType.name,
                price = it.price ?: 0.toBigDecimal(),
                priceDate = it.pricedDate.let { date ->
                    date?.let {
                        it.toString()
                    } ?: ""
                },
                priceHistoryList = bondPriceHistoryList
            )
        }
    }

    override fun findBondList(name: String): List<BondSimpleDto> {
        val likeName = StringBuilder().append("%").append(name).append("%").toString()
        return bondRepository.findByIsinCodeNameLike(likeName)?.map { bond ->
            BondSimpleDto(
                bondId = bond.isinCode,
                bondName = bond.isinCodeName,
                surfaceInterestRate = bond.surfaceInterestRate,
                issuerName = bond.issuer.name,
                issueDate = bond.issueDate.toString(),
                expiredDate = bond.expiredDate.toString(),
                interestPaymentCycle = bond.interestPaymentCycle,
                optionType = bond.optionType?.name,
                securitiesItemKindName = bond.securitiesItemKind.name,
                interestChange = bond.interestChange.name,
                interestType = bond.interestType.name,
                price = bond.price ?: 0.toBigDecimal(),
                priceDate = bond.pricedDate.let { date ->
                    date?.let {
                        it.toString()
                    } ?: ""
                }
            )
        } ?: emptyList()
    }

    override fun getBondListGroupByGrade(): List<BondRankInDto> {
        return bondGradeRankRepository.findAllByOrderByGradeAscRankAsc().map {
            BondRankInDto(
                bondName = it.isinCodeName,
                surfaceInterestRate = it.surfaceInterestRate,
                expiredDate = it.expiredDate,
                grade = it.grade,
                rank = it.rank
            )
        }
    }
}