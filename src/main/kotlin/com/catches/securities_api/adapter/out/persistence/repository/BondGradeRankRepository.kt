package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.bond.BondGradeRank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BondGradeRankRepository: JpaRepository<BondGradeRank, String> {

    fun findAllByOrderByGradeAscRankAsc(): List<BondGradeRank>
}