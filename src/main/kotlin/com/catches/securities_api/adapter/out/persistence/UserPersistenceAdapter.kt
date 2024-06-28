package com.catches.securities_api.adapter.out.persistence

import com.catches.securities_api.adapter.out.persistence.exception.NotFoundException
import com.catches.securities_api.adapter.out.persistence.repository.BondRepository
import com.catches.securities_api.adapter.out.persistence.repository.UserBondInfoRepository
import com.catches.securities_api.adapter.out.persistence.repository.UserRepository
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.UserPort
import com.catches.securities_api.domain.user.User
import com.catches.securities_api.domain.user.UserBondInfo
import com.catches.securities_api.domain.user.UserStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrNull

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userBondInfoRepository: UserBondInfoRepository,
    private val bondRepository: BondRepository
): UserPort {

    @Transactional
    override fun saveUser(id: String): User {
        return userRepository.save(
            User(
                id = id,
                status = UserStatus.ACTIVE,
            )
        )
    }

    @Transactional
    override fun saveUserBond(userId: String, bondId: String) {

        val user = userRepository.findById(userId).getOrNull() ?: throw NotFoundException("User not found")
        val bond = bondRepository.findById(bondId).getOrNull() ?: throw NotFoundException("Bond not found")
        userBondInfoRepository.save(
            UserBondInfo(
                user = user,
                bond = bond,
                bondRate = bond.surfaceInterestRate,
                bondAmount = bond.price,
            )
        )
    }

    override fun getUserBondList(userId: String): List<BondSimpleDto> {
        return userBondInfoRepository.findBondDetailByUserId(userId)?.map {
            BondSimpleDto(
                bondId = it.bond.isinCode,
                bondName = it.bond.isinCodeName,
                surfaceInterestRate = it.bond.surfaceInterestRate,
                issuerName = it.bond.issuer.name,
                issueDate = it.bond.issueDate.toString(),
                expiredDate = it.bond.expiredDate.toString(),
                interestPaymentCycle = it.bond.interestPaymentCycle,
                optionType = it.bond.optionType?.name,
                securitiesItemKindName = it.bond.securitiesItemKind.name,
                interestChange = it.bond.interestChange.name,
                interestType = it.bond.interestType.name,
                price = it.bond.price?: 0.toBigDecimal(),
                priceDate = it.bond.pricedDate.let { date ->
                    date?.let {
                        it.toString()
                    } ?: ""
                }
            )
        }?: emptyList()
    }

    @Transactional
    override fun deleteUserBond(userId: String, bondId: String) {
        try{
            userBondInfoRepository.deleteByUserIdAndBondId(userId = userId, bondId = bondId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}