package com.catches.securities_api.controller

import com.catches.securities_api.adapter.`in`.web.PosController
import com.catches.securities_api.application.port.`in`.PosUseCase
import com.catches.securities_api.controller.fixture.basePosDtoBuild
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
@AutoConfigureWebTestClient
class PosControllerTest : BehaviorSpec({
    val posUseCase = mockk<PosUseCase>()
    val posController = PosController(posUseCase)

    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(posController).build()
    val objectMapper: ObjectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    Given("공모주 일정 조회 요청이 들어온 경우") {

        every { posUseCase.getPosSchedule(any()) } returns listOf(basePosDtoBuild())

        When("년도가 유효하지 않은 경우") {

            val year = "202"

            Then("400 Bad Request를 반환한다") {
                mockMvc.perform(
                    MockMvcRequestBuilders.get("/pos")
                        .param("year", year)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest)
            }
        }
        When("년도가 유효한 경우") {

            val year = "2022"

            Then("200 OK를 반환한다") {
                mockMvc.perform(
                    MockMvcRequestBuilders.get("/pos")
                        .param("year", year)
                ).andExpect(MockMvcResultMatchers.status().isOk)
            }
        }
    }
})