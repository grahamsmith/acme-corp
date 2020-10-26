package com.grahamsmith.acme.networking.models

data class ResponseMeta(
        val status_code: Int,
        val reason: String?
)