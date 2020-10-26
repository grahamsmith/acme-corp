package com.grahamsmith.acme.utils

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getText(): String {

    val t = this.editText?.text ?: ""
    return t.toString()
}