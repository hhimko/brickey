package com.example.brickey.ui.set_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.brickey.databinding.FragmentSetMinifigsPageBinding
import com.example.brickey.di.ViewModelFactories
import com.example.rebrickable.models.Set


class SetMinifigsPageFragment(private val _set: Set) : Fragment() {
    private val _viewModel: SetDetailsViewModel by viewModels {
        ViewModelFactories.setDetailsViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSetMinifigsPageBinding.inflate(inflater)

        return binding.root
    }
}