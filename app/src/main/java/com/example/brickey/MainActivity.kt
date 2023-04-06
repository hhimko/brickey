package com.example.brickey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brickey.di.DependencyServices
import com.example.utility.HttpClient


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        // Configure DI services
        val apiClient = HttpClient("")
        DependencyServices.addSingleton<HttpClient>(apiClient)
    }
}
