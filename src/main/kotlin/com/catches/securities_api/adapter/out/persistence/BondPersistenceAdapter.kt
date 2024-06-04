package com.catches.securities_api.adapter.out.persistence

import com.catches.securities_api.adapter.out.persistence.repository.BondRepository
import com.catches.securities_api.application.port.out.BondPort
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import org.springframework.stereotype.Component
import java.lang.StringBuilder

@Component
class BondPersistenceAdapter(
    private val bondRepository: BondRepository
): BondPort {

    override fun getBondSimpleInfo(name: String): BondSimpleDto? {
        val likeName = StringBuilder().append("%").append(name).append("%").toString()
        return bondRepository.findByIsinCodeNameLike(likeName)?.firstOrNull().let { bond ->
            bond?.let {
                BondSimpleDto(
                    bondName = it.isinCodeName,
                    surfaceInterestRate = it.surfaceInterestRate,
                    issuerName = it.issuer.name,
                    issueDate = it.issueDate,
                    expiredDate = it.expiredDate,
                    interestChange = it.interestChange.name,
                    interestType = it.interestType.name,
                    price = it.price?: 0.toBigDecimal(),
                    priceDate = it.pricedDate.let { date ->
                        date?.let {
                            it.toString()
                        } ?: ""
                    }
                )
            }
        }
    }
}