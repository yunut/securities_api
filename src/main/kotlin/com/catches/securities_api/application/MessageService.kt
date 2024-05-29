package com.catches.securities_api.application

import com.catches.securities_api.application.port.`in`.MessageUseCase
import com.catches.securities_api.application.port.out.MessagePort
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val messagePort: MessagePort
) : MessageUseCase {


    // TODO 메시지 포맷팅 로직이 필요함
    override fun sendMessage(chatId: String, message: String) {
        messagePort.sendMessage(chatId, message)
    }
}