package com.grahamsmith.acme.ui.profiles

import java.lang.RuntimeException

class ProfilesLoadFailureException(message: String) : RuntimeException(message)