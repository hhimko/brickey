package com.example.brickey.ui.search_results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brickey.R
import com.example.brickey.di.ViewModelFactories
import com.example.brickey.databinding.FragmentSearchResultsBinding
import com.example.brickey.models.SetSearchQuery
import com.example.utility.setOnSubmitListener


class SearchResultsFragment : Fragment() {
    private val _navArgs: SearchResultsFragmentArgs by navArgs()
    private lateinit var _binding: FragmentSearchResultsBinding
    private lateinit var _queryModel: SetSearchQuery
    private val _viewModel: SearchResultsViewModel by viewModels {
        ViewModelFactories.searchResultsViewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultsBinding.inflate(inflater)
        _queryModel = _navArgs.searchQuery

        _binding.searchResultsCountTextView.text = ""

        _binding.navigateHomeButton.setOnClickListener {
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToHomeFragment()
            val navController = findNavController()
            navController.navigate(action)
        }

        _binding.setSearchButton.setOnClickListener {
            _queryModel.searchTerm = _binding.searchEditText.text.toString()
            _viewModel.searchSets(_queryModel)
        }

        _binding.searchEditText.setText(_navArgs.searchQuery.searchTerm)
        _binding.searchEditText.setOnSubmitListener {
            _queryModel.searchTerm = _binding.searchEditText.text.toString()
            _viewModel.searchSets(_queryModel)
        }

        _binding.searchCancelButton.setOnClickListener {
            _binding.searchEditText.setText("")
        }

        setupSearchResultsRecyclerView()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.searchSets(_queryModel)
    }

    private fun setupSearchResultsRecyclerView() {
        _binding.searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)

        _viewModel.setsLiveData.observe(viewLifecycleOwner) {
            _binding.searchResultsRecyclerView.adapter = SearchResultsAdapter(_viewModel, it)
        }

        _viewModel.setsCountLiveData.observe(viewLifecycleOwner) {
            _binding.searchResultsCountTextView.text = getString(R.string.search_results_count, it)
        }
    }
}
