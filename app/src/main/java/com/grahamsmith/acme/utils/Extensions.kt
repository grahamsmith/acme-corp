package com.grahamsmith.acme.utils

import androidx.lifecycle.LiveData
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.Deferred

fun TextInputLayout.getText(): String {
    val t = this.editText?.text ?: ""
    return t.toString()
}

inline fun <reified T> Deferred<T>.liveData(): LiveData<T> {
    return androidx.lifecycle.liveData {
        emit(await())
    }
}