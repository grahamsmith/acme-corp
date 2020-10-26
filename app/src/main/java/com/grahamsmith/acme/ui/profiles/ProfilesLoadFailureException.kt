package com.grahamsmith.acme.ui.profiles

import java.lang.RuntimeException

class ProfilesLoadFailureException(val userMessage: String) : RuntimeException()