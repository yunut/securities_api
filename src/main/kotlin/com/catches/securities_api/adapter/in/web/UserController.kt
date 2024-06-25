package com.catches.securities_api.adapter.`in`.web

import com.catches.securities_api.adapter.`in`.web.request.UserBondCreateRequest
import com.catches.securities_api.adapter.`in`.web.request.UserCreateRequest
import com.catches.securities_api.adapter.`in`.web.response.BondResponse
import com.catches.securities_api.adapter.`in`.web.response.MetaBody
import com.catches.securities_api.adapter.`in`.web.response.ResponseBody
import com.catches.securities_api.application.port.`in`.UserUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.RoundingMode


@RestController
class UserController(
    private val userUseCase: UserUseCase
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/create")
    fun createUser(
        @RequestBody user: UserCreateRequest,
    ): ResponseBody {

        userUseCase.createUser(user.id)
        return ResponseBody(
            meta = MetaBody(201, "User created"),
            data = null
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/bond")
    fun getUserBondList(
        @RequestParam("userId") userId: String
    ): ResponseBody {
        val data = userUseCase.getUserBondList(userId)

        return ResponseBody(
            meta = MetaBody(200, "Success"),
            data = data.map {
                BondResponse(
                    bondId = it.bondId,
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user/bond/create")
    fun createUserBond(
        @RequestBody userBond: UserBondCreateRequest,
    ): ResponseBody {

        userUseCase.createUserBond(userBond.userId, userBond.bondId)

        return ResponseBody(
            meta = MetaBody(201, "User created"),
            data = null
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/user/bond")
    fun deleteUserBond(
        @RequestParam("userId") userId: String,
        @RequestParam("bondId") bondId: String
    ): ResponseBody {

        userUseCase.deleteUserBond(userId, bondId)

        return ResponseBody(
            meta = MetaBody(200, "Success"),
            data = null
        )
    }

}