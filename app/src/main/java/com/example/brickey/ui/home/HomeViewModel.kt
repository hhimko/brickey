package com.example.brickey.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.utility.HttpClient


class HomeViewModel(
    private val _apiClient: HttpClient
) : ViewModel() {

    fun getApiClient(): HttpClient {
        return _apiClient
    }
}
