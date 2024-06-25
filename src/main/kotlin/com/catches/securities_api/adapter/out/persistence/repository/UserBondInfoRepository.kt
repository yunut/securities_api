package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.user.UserBondInfo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserBondInfoRepository: JpaRepository<UserBondInfo, UUID> {
}