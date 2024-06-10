package com.catches.securities_api.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "bond_grade_rank")
data class BondGradeRank(

    @Column(name = "grade", nullable = false, length = 5)
    var grade: String,

    @Column(name = "rank", nullable = false)
    var rank: Int,

    @Id
    @Column(name = "isin_code", nullable = false, length = 12)
    var isinCode: String,

    @Column(name = "isin_code_name", nullable = false, length = 100)
    var isinCodeName: String,

    @Column(name = "surface_interest_rate", nullable = false, precision = 15, scale = 10)
    var surfaceInterestRate: BigDecimal,

    @Column(name = "expired_date", nullable = false)
    var expiredDate: LocalDate,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)