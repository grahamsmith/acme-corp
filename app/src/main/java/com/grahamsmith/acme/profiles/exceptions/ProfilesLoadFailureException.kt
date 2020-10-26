package com.grahamsmith.acme.profiles.exceptions

import java.lang.RuntimeException

class ProfilesLoadFailureException(val userMessage: String) : RuntimeException()