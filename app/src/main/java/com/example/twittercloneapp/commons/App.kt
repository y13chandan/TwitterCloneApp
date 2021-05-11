package com.example.twittercloneapp.commons

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.paperdb.Paper;

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}