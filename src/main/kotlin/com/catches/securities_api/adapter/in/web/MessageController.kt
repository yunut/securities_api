package com.catches.securities_api.adapter.`in`.web

import com.catches.securities_api.adapter.`in`.web.request.MessageRequest
import com.catches.securities_api.application.port.out.MessagePort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
class MessageController(
    private val messagePort: MessagePort
) {

    @PostMapping("/send")
    fun sendMessage(
        @RequestBody messageRequest: MessageRequest
    ) {
        messagePort.sendMessage(messageRequest.chatId, messageRequest.message)
    }
}