package com.example.brickey.ui.filtered_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.brickey.databinding.FragmentFilteredSearchBinding
import com.example.brickey.di.ViewModelFactories
import com.example.brickey.models.SetSearchQuery
import com.example.utility.setOnSubmitListener
import java.util.*


class FilteredSearchFragment : Fragment() {
    private val _viewModel: FilteredSearchViewModel by viewModels { ViewModelFactories.filteredSearchViewModelFactory }
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

        val releaseYear = _binding.yearEditText.text.let {
            if (it.isEmpty())
                return@let null

            val value = it.toString().toIntOrNull() ?: return@let null
            if (value < 1000 || value > Calendar.getInstance().get(Calendar.YEAR)) {
                _binding.yearEditText.startAnimation(_viewModel.getShakeAnimation())
                return@onSearch
            }

            return@let value
        }

        val query = SetSearchQuery(searchTerm, null, releaseYear)
        val action = FilteredSearchFragmentDirections.actionFilteredSearchFragmentToSearchResultsFragment(query)

        val navController = findNavController()
        navController.navigate(action)
    }
}
