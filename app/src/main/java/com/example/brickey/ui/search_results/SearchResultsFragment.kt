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
import com.example.brickey.models.SetSearchQuery
import com.example.utility.setOnSubmitListener


class SearchResultsFragment : Fragment() {
    private val _navArgs: SearchResultsFragmentArgs by navArgs()
    private lateinit var query: SetSearchQuery
    private val _viewModel: SearchResultsViewModel by viewModels {
        ViewModelFactories.searchResultsViewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchResultsBinding = FragmentSearchResultsBinding.inflate(inflater)
        query = _navArgs.searchQuery

        binding.searchEditText.setOnSubmitListener {
            query.searchTerm = binding.searchEditText.text.toString()
            _viewModel.searchSets(query)
        }

        binding.searchEditText.setText(_navArgs.searchQuery.searchTerm)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.searchSets(query)
    }
}
