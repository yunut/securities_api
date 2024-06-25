package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.user.UserBondInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserBondInfoRepository: JpaRepository<UserBondInfo, UUID> {


    @Query("SELECT ubi FROM UserBondInfo ubi JOIN ubi.bond b WHERE ubi.user.id = :userId")
    fun findBondDetailByUserId(@Param("userId") userId: String): List<UserBondInfo>

}