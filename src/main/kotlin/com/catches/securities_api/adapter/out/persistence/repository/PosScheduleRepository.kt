package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.PosSchedule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface PosScheduleRepository: JpaRepository<PosSchedule, UUID> {

    @Query("SELECT p FROM PosSchedule p WHERE YEAR(p.posStartDate) = :year ORDER BY p.posStartDate ASC")
    fun findByPosStartDateYear(@Param("year") year: Int): List<PosSchedule>

}