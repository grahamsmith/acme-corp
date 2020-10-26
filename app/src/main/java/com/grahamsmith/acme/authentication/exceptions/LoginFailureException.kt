package com.grahamsmith.acme.authentication.exceptions

class LoginFailureException(val userMessage: String) : RuntimeException()