package com.catches.securities_api.adapter.`in`.web

import com.catches.securities_api.adapter.`in`.web.response.*
import com.catches.securities_api.application.port.`in`.UserUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.RoundingMode

@RestController
data class UserController(
    private val userUseCase: UserUseCase
) {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/bond")
    fun putUserBondHistory(
        @RequestParam("userId") userId: String
    ): ResponseBody {
        //TODO

        val data = userUseCase.putUserBondHistory(userId)

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
}
