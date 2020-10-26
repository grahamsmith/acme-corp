package com.grahamsmith.acme.authentication.exceptions

import java.lang.RuntimeException

class LoginFailureException(val userMessage: String) : RuntimeException()