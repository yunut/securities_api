package com.catches.securities_api.adapter.out.persistence

import com.catches.securities_api.adapter.out.persistence.repository.UserRepository
import com.catches.securities_api.application.port.out.UserPort
import com.catches.securities_api.domain.user.User
import com.catches.securities_api.domain.user.UserStatus
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository
): UserPort {
    override fun saveUser(id: String): User {
        return userRepository.save(
            User(
                id = id,
                status = UserStatus.ACTIVE,
            )
        )
    }

}