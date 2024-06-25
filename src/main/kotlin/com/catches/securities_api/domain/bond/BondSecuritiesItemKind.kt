package com.catches.securities_api.domain.bond

import jakarta.persistence.*

@Entity
@Table(name = "bond_securities_item_kind")
data class BondSecuritiesItemKind(
    @Id
    @Column(name = "code", length = 4, nullable = false)
    var code: String,

    @Column(name = "name", length = 100, nullable = false)
    var name: String
)