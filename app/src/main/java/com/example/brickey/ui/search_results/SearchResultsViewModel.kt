package com.example.brickey.ui.search_results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brickey.models.SetSearchQuery
import com.example.rebrickable.RebrickableApiClient
import kotlinx.coroutines.launch


class SearchResultsViewModel(
    private val _apiClient: RebrickableApiClient
) : ViewModel() {

    fun searchSets(query: SetSearchQuery) {
        viewModelScope.launch {
            val pagedResponse = _apiClient.getSetsAsync(query.searchTerm)
        }
    }
}
