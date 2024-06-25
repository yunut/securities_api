package com.catches.securities_api.web.controller

import com.catches.securities_api.adapter.`in`.web.PosController
import com.catches.securities_api.adapter.`in`.web.UserController
import com.catches.securities_api.application.port.`in`.PosUseCase
import com.catches.securities_api.application.port.`in`.UserUseCase
import com.catches.securities_api.web.controller.fixture.userCreateRequestBuild
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders


@SpringBootTest
@AutoConfigureWebTestClient
class UserControllerTest : BehaviorSpec({
    val userUseCase = mockk<UserUseCase>()
    val userController = UserController(userUseCase)

    val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(userController).build()
    val objectMapper: ObjectMapper = jacksonObjectMapper().registerModule(JavaTimeModule())

    Given("사용자 생성 요청이 들어온 경우") {

        When("사용자 ID가 유효한 경우") {

            val request = userCreateRequestBuild()
            every { userUseCase.createUser(any()) } just Runs

            Then("200 OK를 반환한다") {
                mockMvc.perform(
                    MockMvcRequestBuilders.post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andExpect(MockMvcResultMatchers.status().isCreated)
            }
        }
    }
})