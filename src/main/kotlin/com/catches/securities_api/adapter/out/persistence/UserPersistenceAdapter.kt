package com.catches.securities_api.adapter.out.persistence

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.UserPort
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(

): UserPort {
    override fun putUserBondHistory(name: String): BondSimpleDto? {
        TODO("Not yet implemented")
    }
}