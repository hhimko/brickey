package com.example.brickey

import android.app.Application
import com.example.brickey.di.DependencyServices
import com.example.rebrickable.RebrickableApiClient


class BrickeyApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // Configure DI services
        val apiClient = RebrickableApiClient(auth_token = BuildConfig.API_KEY)
        DependencyServices.addSingleton<RebrickableApiClient>(apiClient)
    }
}
