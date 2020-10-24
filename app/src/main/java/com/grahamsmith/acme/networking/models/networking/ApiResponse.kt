package com.grahamsmith.acme.networking.models.networking

interface ApiResponse<T> {
    val meta: ResponseMeta
    val data: T
}