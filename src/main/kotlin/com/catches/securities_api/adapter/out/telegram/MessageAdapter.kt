package com.catches.securities_api.adapter.out.telegram

import com.catches.securities_api.adapter.out.telegram.utli.TelegramClient
import com.catches.securities_api.application.port.out.MessagePort
import org.springframework.stereotype.Component

@Component
class MessageAdapter(
    private val telegramClient: TelegramClient
): MessagePort {

    override fun sendMessage(chatId: String, message: String) {
        telegramClient.sendMessage(chatId, message)
    }
}