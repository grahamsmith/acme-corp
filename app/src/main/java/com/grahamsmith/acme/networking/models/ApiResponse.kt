package com.grahamsmith.acme.networking.models

interface ApiResponse<T> {
    val meta: ResponseMeta
    val data: T
}