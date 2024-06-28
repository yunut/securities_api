package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.bond.BondPriceHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate


interface BondPriceHistoryRepository: JpaRepository<BondPriceHistory, Long> {
    fun findAllByCode(code: String): List<BondPriceHistory>?
}
