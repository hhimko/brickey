package com.example.utility

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.*


class HttpClient private constructor (private val _baseURL: URL) {
    constructor(base_url: String)
        : this(URL(base_url))

    private val _defaults = mutableMapOf<String, String>()
    val JSON get() = Json { ignoreUnknownKeys = true }

    var readTimeout: Int = 5000


    fun addDefaultRequestProperty(key: String, value: String) {
        if (_defaults.containsKey(key) && value != _defaults[key]) {
            error("Default property `$key` already set to `${_defaults[key]}`")
        }

        _defaults[key] = value
    }

    fun getRaw(endpoint: String): Response<String> {
        val url = URL(_baseURL, endpoint)

        (url.openConnection() as? HttpURLConnection)?.run {
            requestMethod = "GET"
            setRequestProperty("Accept", "application/json")
            readTimeout = this@HttpClient.readTimeout
            doInput = true
            doOutput = false

            _defaults.forEach {
                (key, value) -> setRequestProperty(key, value)
            }

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

    inline fun <reified T> get(endpoint: String): Response<T?> {
        val raw = getRaw(endpoint)
        if (raw.isSuccess) {
            val res = JSON.decodeFromString<T>(raw.result)
            return Response(raw.code, res)
        }

        return Response(raw.code, null)
    }

    suspend inline fun <reified T> getAsync(endpoint: String): Response<T?> {
        return withContext(Dispatchers.IO) {
            return@withContext get(endpoint)
        }
    }


    data class Response<T>(
        val code: Int,
        val result: T
    ) {
        val isSuccess: Boolean get() { return code == HttpURLConnection.HTTP_OK }
    }
}
