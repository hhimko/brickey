package com.example.rebrickable

import com.example.rebrickable.models.PagedResponse
import com.example.utility.HttpClient
import com.example.rebrickable.models.Set


class RebrickableApiClient(private val auth_token: String) {
    private val _httpClient = HttpClient("https://rebrickable.com/api/v3/")


    suspend fun getSetsAsync(searchTerm: String): PagedResponse<Set> {
        val res = _httpClient.getRawAsync("lego/sets/")
        println(res.isSuccess)

        return PagedResponse(null, null, emptyArray())
    }
}
