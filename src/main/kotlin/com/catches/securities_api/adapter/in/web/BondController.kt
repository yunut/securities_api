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

    // TODO SearchBondList를 하여 채권 이름으로 검색 할수 있는 API로 처리해야 한다.
    /**
     * TODO
     * flow
     *  1. 채권 이름으로 like 검색을 하여 채권 이름 리스트를 가져온다.
     *  2. 텔레그램 메시지에서 해당 리스트를 선택할수 있는 메시지를 보여준다.
     *  3. 메시지가 선택이 되면 해당 채권에 대한 정보를 가져온다.
     *  -> 등록도 채권을 먼저 검색하고 등록하도록 만들어야 할것 같다.
     */
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
