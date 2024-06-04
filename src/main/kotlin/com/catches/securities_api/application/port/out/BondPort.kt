package com.catches.securities_api.application.port.out

import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto

interface BondPort {

    fun getBondSimpleInfo(name: String): BondSimpleDto?
}