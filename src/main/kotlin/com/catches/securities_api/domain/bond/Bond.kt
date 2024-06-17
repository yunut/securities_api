package com.catches.securities_api.domain.bond

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "bond")
data class Bond(
    @Id
    @Column(name = "isin_code", length = 12, nullable = false)
    var isinCode: String,

    @Column(name = "isin_code_name", length = 100, nullable = false)
    var isinCodeName: String,

    @Column(name = "crno", length = 13)
    var crno: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_name", nullable = false)
    var issuer: BondIssuer,

    @Column(name = "issue_date", nullable = false)
    var issueDate: LocalDate,

    @Column(name = "issue_format_name", length = 100, nullable = false)
    var issueFormatName: String,

    @Column(name = "surface_interest_rate", precision = 15, scale = 10, nullable = false)
    var surfaceInterestRate: BigDecimal,

    @Column(name = "expired_date", nullable = false)
    var expiredDate: LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "securities_item_kind_code", nullable = false)
    var securitiesItemKind: BondSecuritiesItemKind,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_change_code", nullable = false)
    var interestChange: BondInterestChange,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_type_code", nullable = false)
    var interestType: BondInterestType,

    @Column(name = "issue_currency_code", length = 3, nullable = false)
    var issueCurrencyCode: String,

    @Column(name = "price", precision = 15, scale = 2)
    var price: BigDecimal? = null,

    @Column(name = "priced_date")
    var pricedDate: LocalDate? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)