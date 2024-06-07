package com.catches.securities_api.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "pos_schedule")
data class PosSchedule(
    @Id
    @Column(name = "id", length = 36, nullable = false)
    var id: String,

    @Column(name = "pos_name", length = 100, nullable = false)
    var posName: String,

    @Column(name = "pos_start_date", nullable = false)
    var posStartDate: LocalDate,

    @Column(name = "pos_end_date", nullable = false)
    var posEndDate: LocalDate,

    @Column(name = "pos_confirmed_price")
    var posConfirmedPrice: Int? = null,

    @Column(name = "pos_desired_min_price", nullable = false)
    var posDesiredMinPrice: Int,

    @Column(name = "pos_desired_max_price", nullable = false)
    var posDesiredMaxPrice: Int,

    @Column(name = "pos_competition_rate", length = 20)
    var posCompetitionRate: BigDecimal? = null,

    @Column(name = "pos_taken_company", length = 100, nullable = false)
    var posTakenCompany: String
)