package com.example.utility

import java.net.HttpURLConnection
import java.net.URL


class HttpClient(private val base_url: String) {

    fun addDefaultRequestProperty() {

    }

    fun get(endpoint: String): String {
        val url = URL(base_url + endpoint)

        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
        }

        return "an error occurred"
    }

    fun getAsync(endpoint: String): String {
        return get(endpoint)
    }
}
