package com.example.brickey.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.brickey.ui.filtered_search.FilteredSearchViewModel
import com.example.brickey.ui.home.HomeViewModel
import com.example.brickey.ui.search_results.SearchResultsViewModel
import com.example.brickey.ui.set_details.SetDetailsViewModel
import com.example.rebrickable.RebrickableApiClient


// Static ViewModel factory implementations
class ViewModelFactories {
    companion object {
        interface ViewModelFactory<VM: ViewModel>: ViewModelProvider.Factory {
            fun inject(): VM

            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel> create(modelClass: Class<T>): T {
                return inject() as T
            }
        }

        val homeViewModelFactory = object: ViewModelFactory<HomeViewModel> {
            override fun inject(): HomeViewModel {
                return HomeViewModel()
            }
        }

        val filteredSearchViewModelFactory = object: ViewModelFactory<FilteredSearchViewModel> {
            override fun inject(): FilteredSearchViewModel {
                return FilteredSearchViewModel()
            }
        }

        val searchResultsViewModelFactory = object: ViewModelFactory<SearchResultsViewModel> {
            override fun inject(): SearchResultsViewModel {
                val apiClient = DependencyServices.getInstance<RebrickableApiClient>()

                return SearchResultsViewModel(apiClient)
            }
        }

        val setDetailsViewModelFactory = object: ViewModelFactory<SetDetailsViewModel> {
            override fun inject(): SetDetailsViewModel {
                val apiClient = DependencyServices.getInstance<RebrickableApiClient>()

                return SetDetailsViewModel(apiClient)
            }
        }
    }
}
