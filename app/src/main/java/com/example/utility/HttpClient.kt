package com.example.utility

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.*


class HttpClient private constructor (private val _baseURL: URL) {
    constructor(base_url: String)
        : this(URL(base_url))

    var readTimeout: Int = 5000


    fun addDefaultRequestProperty() {

    }

    fun getRaw(endpoint: String): Response<String> {
        val url = URL(_baseURL, endpoint)

        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            readTimeout = this@HttpClient.readTimeout
            doInput = true
            doOutput = false
            setRequestProperty("Accept", "application/json")

            var result = responseMessage
            if (responseCode == HttpURLConnection.HTTP_OK){
                result = inputStream.bufferedReader().use {
                    it.readText()
                }
            }

            return Response(responseCode, result)
        }

        return Response(HttpURLConnection.HTTP_INTERNAL_ERROR, "Unexpected error occurred")
    }

    suspend fun getRawAsync(endpoint: String): Response<String> {
        return withContext(Dispatchers.IO) {
            return@withContext getRaw(endpoint)
        }
    }


    data class Response<T>(
        val code: Int,
        val result: T
    ) {
        val isSuccess: Boolean get() { return code == HttpURLConnection.HTTP_OK }
    }
}
