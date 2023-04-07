package com.example.brickey.ui.home

import androidx.lifecycle.ViewModel
import com.example.utility.HttpClient


class HomeViewModel(
    private val _apiClient: HttpClient
) : ViewModel() {

    fun getApiClient(): HttpClient {
        return _apiClient
    }
}
