package com.catches.securities_api.adapter.`in`.web

import com.catches.securities_api.adapter.`in`.web.response.BondResponse
import com.catches.securities_api.adapter.`in`.web.response.MetaBody
import com.catches.securities_api.adapter.`in`.web.response.ResponseBody
import com.catches.securities_api.application.port.out.BondPort
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.RoundingMode

@RestController
data class BondController(
    private val bondPort: BondPort
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/bond")
    fun getBondList(
        @RequestParam("name") name: String
    ): ResponseBody {
        val data = bondPort.getBondSimpleInfo(name)

        return ResponseBody(
            meta = MetaBody(200, "Success"),
            data = data.let {
                BondResponse(
                    bondName = it!!.bondName,
                    surfaceInterestRate = it.surfaceInterestRate.setScale(2, RoundingMode.DOWN).toDouble(),
                    issuerName = it.issuerName,
                    issueDate = it.issueDate.toString(),
                    expiredDate = it.expiredDate.toString(),
                    interestChange = it.interestChange
                )
            }
        )
    }
}
