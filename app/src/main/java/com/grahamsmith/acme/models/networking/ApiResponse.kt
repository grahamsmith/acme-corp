package com.grahamsmith.acme.models.networking

interface ApiResponse<T> {
    val meta: ResponseMeta
    val data: T
}