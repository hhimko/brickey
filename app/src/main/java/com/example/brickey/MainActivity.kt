package com.example.brickey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.utility.HttpClient


class MainActivity : AppCompatActivity() {
    private lateinit var searchQueryEditText: EditText
    private val apiClient = HttpClient("https://rebrickable.com/api/v3/");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }
}
