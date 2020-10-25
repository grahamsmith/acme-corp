package com.grahamsmith.acme.authentication.exceptions

import java.lang.RuntimeException

class LoginFailureException(message: String) : RuntimeException(message)