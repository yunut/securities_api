package com.catches.securities_api.adapter.`in`.web

import com.catches.securities_api.adapter.`in`.web.response.*
import com.catches.securities_api.application.port.`in`.BondUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.RoundingMode

@RestController
data class BondController(
    private val bondUseCase: BondUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bond")
    fun getBondList(
        @RequestParam("name") name: String
    ): ResponseBody {
        val data = bondUseCase.getBondSimpleInfo(name)

        return ResponseBody(
            meta = MetaBody(200, "Success"),
            data = data?.let {
                BondResponse(
                    bondName = it!!.bondName,
                    surfaceInterestRate = it.surfaceInterestRate.setScale(2, RoundingMode.DOWN).toDouble(),
                    issuerName = it.issuerName,
                    issueDate = it.issueDate.toString(),
                    expiredDate = it.expiredDate.toString(),
                    interestChange = it.interestChange,
                    interestType = it.interestType,
                    price = it.price.toInt(),
                    priceDate = it.priceDate
                )
            }
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bond/list")
    fun getBondList(
    ): ResponseBody {
        val data = bondUseCase.getBondListGroupByGrade()

        return ResponseBody(
            meta = MetaBody(200, "Success"),
            data = data.map {
                BondRankResponse(
                    grade = it.grade,
                    bondList = it.bondList.map { bond ->
                        BondRankResponseBody(
                            name = bond.name,
                            surfaceInterestRate = bond.surfaceInterestRate,
                            expiredDate = bond.expiredDate,
                            grade = bond.grade
                        )
                    }
                )
            }
        )
    }
}
