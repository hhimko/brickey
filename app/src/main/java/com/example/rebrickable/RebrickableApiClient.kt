package com.example.rebrickable

import android.util.LruCache
import com.example.rebrickable.models.PagedResponse
import com.example.utility.HttpClient
import com.example.rebrickable.models.Set
import com.example.rebrickable.models.Theme
import com.example.rebrickable.models.partial.ThemePartial


class RebrickableApiClient(auth_token: String, cache_size: Int = 100) {
    private val _httpClient = HttpClient("https://rebrickable.com/api/v3/")
    private val _themesCache: LruCache<Int, ThemePartial>
    var pageSize: Int = 50

    var themesCacheSize: Int
        get() = _themesCache.size()
        set(value) = _themesCache.resize(value)

    init {
        _httpClient.addDefaultRequestProperty("Authorization", "key $auth_token")
        _themesCache = LruCache(cache_size)
    }


    suspend fun getSetsAsync(searchTerm: String): PagedResponse<Set>? {
        val params = HttpClient.buildParams(mapOf(
            "search" to searchTerm, "page_size" to pageSize
        ))

        return _httpClient.getAsync<PagedResponse<Set>>("lego/sets?$params").result
    }

    suspend fun getThemeByIdAsync(id: Int): Theme? {
        val cachedPartial = _themesCache.get(id)
        val partial: ThemePartial

        if (cachedPartial != null) {
            partial = cachedPartial
        } else {
            val res = _httpClient.getAsync<ThemePartial>("lego/themes/$id/").result
            partial = res ?: return null
            _themesCache.put(id, partial)
        }

        return Theme.fromPartial(this, partial)
    }
}
