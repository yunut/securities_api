package com.catches.securities_api.adapter.out.telegram.config

import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

@Configuration
class TelegramConfig {

    private lateinit var telegramToken: String

    // 환경변수에서 값 가져오기
    @PostConstruct
    fun init() {
        val token = System.getenv("TELEGRAM_BOT_TOKEN")
        telegramToken = token
    }

    fun getTelegramToken(): String {
        return telegramToken
    }
}