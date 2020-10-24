package com.grahamsmith.acme.networking.models.networking

data class ResponseMeta(
        private val status_code: Int,
        private val reason: String?
)