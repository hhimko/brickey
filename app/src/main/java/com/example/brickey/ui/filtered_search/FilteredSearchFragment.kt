package com.example.brickey.ui.filtered_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.brickey.databinding.FragmentFilteredSearchBinding
import com.example.brickey.di.ViewModelFactories
import com.example.brickey.models.SetSearchQuery
import com.example.brickey.ui.home.HomeFragmentDirections
import com.example.brickey.ui.home.HomeViewModel
import com.example.utility.setOnSubmitListener


class FilteredSearchFragment : Fragment() {
    private val _viewModel: HomeViewModel by viewModels { ViewModelFactories.filteredSearchViewModelFactory }
    private lateinit var _binding: FragmentFilteredSearchBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentFilteredSearchBinding = FragmentFilteredSearchBinding.inflate(inflater)
        _binding = binding

        binding.navigateBackButton.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }

        binding.searchEditText.setOnSubmitListener { onSearch() }
        binding.searchButton.setOnClickListener { onSearch() }

        return binding.root
    }

    private fun onSearch() {
        val searchTerm = _binding.searchEditText.text.toString()

        if (searchTerm.isEmpty()){
            return
        }

        val query = SetSearchQuery(searchTerm)
        val action = FilteredSearchFragmentDirections.actionFilteredSearchFragmentToSearchResultsFragment(query)

        val navController = findNavController()
        navController.navigate(action)
    }
}
