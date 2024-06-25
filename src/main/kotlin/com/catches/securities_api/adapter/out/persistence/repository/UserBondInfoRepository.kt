package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.user.UserBondInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserBondInfoRepository: JpaRepository<UserBondInfo, UUID> {


    @Query("SELECT ubi FROM UserBondInfo ubi JOIN ubi.bond b WHERE ubi.user.id = :userId")
    fun findBondDetailByUserId(@Param("userId") userId: String): List<UserBondInfo>

    @Modifying
    @Query("delete from UserBondInfo ubi where ubi.user.id = :userId and ubi.bond.isinCode = :bondId")
    fun deleteByUserIdAndBondId(userId: String, bondId: String)
}