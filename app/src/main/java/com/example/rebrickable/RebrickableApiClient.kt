package com.example.rebrickable

import android.util.LruCache
import com.example.rebrickable.models.Minifig
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


    suspend fun <T> queryNextPage(current_page: PagedResponse<T>): PagedResponse<T>? {
        assert(current_page.next != null)

        val endpoint = current_page.next!!.removePrefix(_httpClient.baseURL)
        return _httpClient.getAsync<PagedResponse<T>>(endpoint).result
    }

    suspend fun getSetsAsync(search_term: String): PagedResponse<Set>? {
        val params = HttpClient.buildParams(mapOf(
            "search" to search_term, "page_size" to pageSize
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

    suspend fun getMinifigsForSetNum(set_num: String): List<Minifig>? {
        val params = HttpClient.buildParams(mapOf(
            "in_set_num" to set_num, "page_size" to pageSize
        ))

        val res = _httpClient.getAsync<PagedResponse<Minifig>>("lego/minifigs?$params").result
        var page = res ?: return null
        val minifigs = page.results.toMutableList()

        while (page.next != null) {
            page = queryNextPage(page) ?: return minifigs
            minifigs += page.results
        }

        return minifigs
    }
}
