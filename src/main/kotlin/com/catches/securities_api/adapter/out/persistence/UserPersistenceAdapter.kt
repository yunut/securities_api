package com.catches.securities_api.adapter.out.persistence

import com.catches.securities_api.adapter.out.persistence.exception.NotFoundException
import com.catches.securities_api.adapter.out.persistence.repository.BondRepository
import com.catches.securities_api.adapter.out.persistence.repository.UserBondInfoRepository
import com.catches.securities_api.adapter.out.persistence.repository.UserRepository
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
                bondId = bond.isinCode,
                bondRate = bond.surfaceInterestRate,
                bondAmount = bond.price,
            )
        )
    }

}