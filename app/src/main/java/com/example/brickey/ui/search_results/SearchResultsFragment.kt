package com.example.brickey.ui.search_results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.brickey.di.ViewModelFactories
import com.example.brickey.databinding.FragmentSearchResultsBinding


class SearchResultsFragment : Fragment() {
    private val _navArgs: SearchResultsFragmentArgs by navArgs()
    private val _viewModel: SearchResultsViewModel by viewModels {
        ViewModelFactories.searchResultsViewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchResultsBinding = FragmentSearchResultsBinding.inflate(inflater)

        binding.searchEditText.setText(_navArgs.searchQueryString)
        return binding.root
    }
}
