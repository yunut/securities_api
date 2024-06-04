package com.catches.securities_api.adapter.out.persistence.repository

import com.catches.securities_api.domain.Bond
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BondRepository: JpaRepository<Bond, String> {

    fun findByIsinCodeNameLike(name: String): List<Bond>?
}