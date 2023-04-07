package com.example.brickey

import android.app.Application
import com.example.brickey.di.DependencyServices
import com.example.utility.HttpClient


class BrickeyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // Configure DI services
        val apiClient = HttpClient("")
        DependencyServices.addSingleton<HttpClient>(apiClient)
    }
}
