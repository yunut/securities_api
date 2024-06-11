package com.catches.securities_api.web.controller

import com.catches.securities_api.adapter.`in`.web.BondController
import com.catches.securities_api.application.port.`in`.BondUseCase
import com.catches.securities_api.web.controller.fixture.bondSimpleDtoBuild
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
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
class BondControllerTest: BehaviorSpec ({

    val bondUseCase = mockk<BondUseCase>()
    val bondController = BondController(bondUseCase)

    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(bondController).build()
    val objectMapper: ObjectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())


    Given("공모주 정보 조회 요청이 들어온 경우") {
        When("공모주 이름이 유효한 경우") {
            every { bondUseCase.getBondSimpleInfo(any()) } returns bondSimpleDtoBuild()
            val name = "Samsung76"

            Then("200 OK와 데이터를 반환한다") {
                mockMvc.perform(
                    MockMvcRequestBuilders.get("/bond")
                        .param("name", name)
                ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn().response.contentAsString.let {
                        objectMapper.readTree(it).get("data").get("bondName").textValue().shouldBe("Samsung76")
                    }
            }
        }
        When("공모주 이름이 유효하지 않은 경우") {
            every { bondUseCase.getBondSimpleInfo(any()) } returns null
            val name = "이건 공모주 이름이 아니에요"

            Then("200 OK와 null 데이터를 반환한다.") {
                mockMvc.perform(
                    MockMvcRequestBuilders.get("/bond")
                        .param("name", name)
                ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn().response.contentAsString.let {
                        objectMapper.readTree(it).get("data").isNull.shouldBe(true)
                    }

            }
        }
    }
    Given("등급별 공모주 조회 요청이 들어온 경우") {
        When("등급별 공모주 조회 요청이 들어온 경우") {

            every { bondUseCase.getBondListGroupByGrade() } returns listOf()

            Then("200 OK와 데이터를 반환한다") {
                mockMvc.perform(
                    MockMvcRequestBuilders.get("/bond/list")
                ).andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn().response.contentAsString.let {
                        objectMapper.readTree(it).get("data").isNull.shouldBe(false)
                    }
            }
        }
    }
})