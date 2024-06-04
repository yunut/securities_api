package com.catches.securities_api.application

import com.catches.securities_api.application.port.`in`.BondUseCase
import com.catches.securities_api.application.port.`in`.dto.BondSimpleDto
import com.catches.securities_api.application.port.out.BondPort
import org.springframework.stereotype.Service

@Service
class BondService(
    private val bondPort: BondPort,
): BondUseCase {

    override fun getBondSimpleInfo(name: String): BondSimpleDto? {
        return bondPort.getBondSimpleInfo(name)
    }

}