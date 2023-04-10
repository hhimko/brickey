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
import com.example.utility.setOnSubmitListener


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels { ViewModelFactories.homeViewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)
        val nav_controller = findNavController()
    Direction
        binding.searchEditText.setOnSubmitListener {
            nav_controller.navigate(R.id.action_homeFragment_to_searchResultsFragment)
        }

        return binding.root
    }
}
