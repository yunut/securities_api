package com.catches.securities_api.adapter.`in`.web.response

data class ResponseBody(
    val meta: MetaBody,
    val data: Any? = null,
)

data class MetaBody(
    val code: Int,
    val message: String? = "",
)