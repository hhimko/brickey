package com.example.rebrickable

import com.example.rebrickable.models.PagedResponse
import com.example.utility.HttpClient
import com.example.rebrickable.models.Set


class RebrickableApiClient(auth_token: String) {
    private val _httpClient = HttpClient("https://rebrickable.com/api/v3/")
    var pageSize: Int = 100

    init {
        _httpClient.addDefaultRequestProperty("Authorization", "key $auth_token")
    }


    suspend fun getSetsAsync(searchTerm: String): PagedResponse<Set> {
        val params = HttpClient.buildParams(mapOf(
            "search" to searchTerm, "page_size" to pageSize
        ))
        val res = _httpClient.getAsync<PagedResponse<Set>>("lego/sets?$params")
        return res.result ?: PagedResponse(null, null, emptyList())
    }
}
