package com.catches.securities_api.domain

import jakarta.persistence.*

@Entity
@Table(name = "bond_interest_change")
data class BondInterestChange(
    @Id
    @Column(name = "code", length = 1, nullable = false)
    var code: String,

    @Column(name = "name", length = 100, nullable = false)
    var name: String
)