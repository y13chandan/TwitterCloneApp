package com.example.twittercloneapp.extensions

import android.util.Patterns

internal fun String.isValidEmail(): Boolean
        = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

internal val String.isValidMobile: Boolean
    get() =  Patterns.PHONE.matcher(this).matches();
