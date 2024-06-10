package com.catches.securities_api.adapter.`in`.web

import com.catches.securities_api.adapter.`in`.web.response.MetaBody
import com.catches.securities_api.adapter.`in`.web.response.PosResponse
import com.catches.securities_api.adapter.`in`.web.response.ResponseBody
import com.catches.securities_api.application.port.`in`.PosUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.RoundingMode

@RestController
class PosController(
    private val posUseCase: PosUseCase,
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/pos")
    fun getPosSchedule(
        @RequestParam("year") year: String
    ): ResponseBody {
        val data = posUseCase.getPosSchedule(year)

        return ResponseBody(
            meta = MetaBody(200, "Success"),
            data = data.map {
                PosResponse(
                    posName = it.posName,
                    posStartDate = it.posStartDate.toString(),
                    posEndDate = it.posEndDate.toString(),
                    posConfirmedPrice = it.posConfirmedPrice,
                    posDesiredMinPrice = it.posDesiredMinPrice,
                    posDesiredMaxPrice = it.posDesiredMaxPrice,
                    posCompetitionRate = it.posCompetitionRate?.setScale(2, RoundingMode.DOWN)?.toDouble(),
                    posTakenCompany = it.posTakenCompany
                )
            }
        )
    }
}