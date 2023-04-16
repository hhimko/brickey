package com.example.rebrickable

import com.example.rebrickable.models.PagedResponse
import com.example.utility.HttpClient
import com.example.rebrickable.models.Set


class RebrickableApiClient(auth_token: String) {
    private val _httpClient = HttpClient("https://rebrickable.com/api/v3/")

    init {
        _httpClient.addDefaultRequestProperty("Authorization", "key $auth_token")
    }


    suspend fun getSetsAsync(searchTerm: String): PagedResponse<Set> {
        val res = _httpClient.getAsync<PagedResponse<Set>>("lego/sets/")
        return res.result ?: PagedResponse(null, null, emptyList())
    }
}
