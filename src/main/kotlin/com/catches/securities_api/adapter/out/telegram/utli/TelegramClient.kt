package com.catches.securities_api.adapter.out.telegram.utli

import com.catches.securities_api.adapter.out.telegram.config.TelegramConfig
import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.entities.ChatId
import org.springframework.stereotype.Component

@Component
class TelegramClient(
    private val telegramConfig: TelegramConfig
) {
    private val telegramBot: Bot = bot {
        token = telegramConfig.getTelegramToken()
    }

    fun sendMessage(chatId: String, message: String) {
        val chatId = ChatId.fromId(chatId.toLong())
        telegramBot.sendMessage(chatId, message)
    }
}