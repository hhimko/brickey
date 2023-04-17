package com.example.brickey.ui.search_results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brickey.models.SetSearchQuery
import com.example.rebrickable.RebrickableApiClient
import kotlinx.coroutines.launch
import com.example.rebrickable.models.Set


class SearchResultsViewModel(
    private val _apiClient: RebrickableApiClient
) : ViewModel() {
    private val _setsLD = MutableLiveData<List<Set>>()
    val setsLiveData: LiveData<List<Set>> = _setsLD

    fun searchSets(query: SetSearchQuery) {
        viewModelScope.launch {
            val pagedResponse = _apiClient.getSetsAsync(query.searchTerm)
            _setsLD.value = pagedResponse?.results ?: emptyList()
        }
    }

    fun loadSetTheme(set: Set) {
        if (set.theme != null)
            return

        viewModelScope.launch {
            set.theme = _apiClient.getThemeByIdAsync(set.themeId)
        }
    }
}
