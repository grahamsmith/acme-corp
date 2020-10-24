package com.grahamsmith.acme.models.networking

data class ResponseMeta(
        private val status_code: Int,
        private val reason: String?
)