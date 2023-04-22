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
import com.example.rebrickable.models.Set


class SearchResultsFragment : Fragment() {
    private val _navArgs: SearchResultsFragmentArgs by navArgs()
    private lateinit var _queryModel: SetSearchQuery
    private val _viewModel: SearchResultsViewModel by viewModels {
        ViewModelFactories.searchResultsViewModelFactory
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchResultsBinding.inflate(inflater)
        _queryModel = _navArgs.searchQuery

        binding.searchResultsCountTextView.text = ""

        binding.navigateHomeButton.setOnClickListener {
            val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToHomeFragment()
            val navController = findNavController()
            navController.navigate(action)
        }

        binding.setSearchButton.setOnClickListener {
            _queryModel.searchTerm = binding.searchEditText.text.toString()
            _viewModel.searchSets(_queryModel)
        }

        binding.searchEditText.setText(_navArgs.searchQuery.searchTerm)
        binding.searchEditText.setOnSubmitListener {
            _queryModel.searchTerm = binding.searchEditText.text.toString()
            _viewModel.searchSets(_queryModel)
        }

        binding.searchCancelButton.setOnClickListener {
            binding.searchEditText.setText("")
        }

        setupSearchResultsRecyclerView(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.searchSets(_queryModel)
    }

    private fun setupSearchResultsRecyclerView(binding: FragmentSearchResultsBinding) {
        binding.searchResultsRecyclerView.layoutManager = LinearLayoutManager(context)

        _viewModel.setsLiveData.observe(viewLifecycleOwner) {
            binding.searchResultsRecyclerView.adapter = SearchResultsAdapter(
                _viewModel, ::onRecyclerViewItemClick, it
            )
        }

        _viewModel.setsCountLiveData.observe(viewLifecycleOwner) {
            val text = resources.getQuantityString(R.plurals.search_results_count, it, it)
            binding.searchResultsCountTextView.text = text
        }
    }

    private fun onRecyclerViewItemClick(set: Set) {
        val action = SearchResultsFragmentDirections.actionSearchResultsFragmentToSetDetailsFragment(set)
        val navController = findNavController()
        navController.navigate(action)
    }
}
