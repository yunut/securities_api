package com.catches.securities_api.domain

import jakarta.persistence.*

@Entity
@Table(name = "bond_issuer")
data class BondIssuer(
    @Id
    @Column(name = "name", length = 100, nullable = false)
    var name: String,

    @Column(name = "crno", length = 13, nullable = false)
    var crno: String,

    @Column(name = "grade", length = 5)
    var grade: String? = null
)