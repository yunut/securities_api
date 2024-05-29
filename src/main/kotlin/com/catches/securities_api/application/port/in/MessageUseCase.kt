package com.catches.securities_api.application.port.`in`

interface MessageUseCase {

    fun sendMessage(chatId: String, message: String)
}