package com.catches.securities_api.domain.bond

import jakarta.persistence.*

@Entity
@Table(name = "bond_option_type")
class BondOptionType(
    @Id
    @Column(name = "code", length = 4, nullable = false)
    var code: String? = null,

    @Column(name = "name", length = 100)
    var name: String? = null
)