package com.grahamsmith.acme.profiles.exceptions

class ProfilesLoadFailureException(val userMessage: String) : RuntimeException()