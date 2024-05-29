package com.catches.securities_api.application.port.out

interface MessagePort {

    fun sendMessage(chatId: String, message: String)
}