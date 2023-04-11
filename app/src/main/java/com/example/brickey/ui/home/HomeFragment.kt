package com.example.brickey.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.brickey.R
import com.example.brickey.databinding.FragmentHomeBinding
import com.example.brickey.di.ViewModelFactories
import com.example.brickey.ui.search_results.SearchResultsFragment
import com.example.brickey.ui.search_results.SearchResultsFragmentDirections
import com.example.utility.setOnSubmitListener


class HomeFragment : Fragment() {
    private val _viewModel: HomeViewModel by viewModels { ViewModelFactories.homeViewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)
        val navController = findNavController()

        binding.searchEditText.setOnSubmitListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToSearchResultsFragment(binding.searchEditText.text.toString())
            navController.navigate(action)
        }

        return binding.root
    }
}
