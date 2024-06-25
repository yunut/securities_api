package com.catches.securities_api.domain.bond

import jakarta.persistence.*

@Entity
@Table(name = "bond_interest_type")
class BondInterestType(
    @Id
    @Column(name = "code", length = 1, nullable = false)
    var code: String,

    @Column(name = "name", length = 100, nullable = false)
    var name: String
)