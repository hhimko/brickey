package com.example.brickey.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.brickey.databinding.FragmentHomeBinding
import com.example.brickey.di.ViewModelFactories
import com.example.brickey.models.SetSearchQuery
import com.example.utility.setOnSubmitListener


class HomeFragment : Fragment() {
    private val _viewModel: HomeViewModel by viewModels { ViewModelFactories.homeViewModelFactory }
    private lateinit var _binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)
        _binding = binding

        binding.searchEditText.setOnSubmitListener { onSearch() }
        binding.searchButton.setOnClickListener { onSearch() }
        binding.searchIconButton.setOnClickListener { onSearch() }
        binding.buttonTop.setOnClickListener { onTopButtonClick() }

        return binding.root
    }

    private fun onSearch() {
        val searchTerm = _binding.searchEditText.text.toString()
        if (searchTerm.isEmpty()) {
            _binding.searchEditText.startAnimation(_viewModel.getShakeAnimation())
            return
        }

        val query = SetSearchQuery(searchTerm)
        val action = HomeFragmentDirections.actionHomeFragmentToSearchResultsFragment(query)

        val navController = findNavController()
        navController.navigate(action)
    }

    private fun onTopButtonClick() {
        val action = HomeFragmentDirections.actionHomeFragmentToFilteredSearchFragment()

        val navController = findNavController()
        navController.navigate(action)
    }
}
