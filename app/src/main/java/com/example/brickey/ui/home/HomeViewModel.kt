package com.example.brickey.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.utility.HttpClient


class HomeViewModel(
    private val _apiClient: HttpClient
) : ViewModel() {

    // Custom Factory for dependency injection
    companion object Factory: ViewModelProvider.Factory {
        override fun <T: ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val api_client = HttpClient("")

            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(api_client) as T
        }
    }
}