package com.example.brickey.ui.set_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.brickey.R
import com.example.brickey.databinding.FragmentSetDescriptionPageBinding
import com.example.brickey.di.ViewModelFactories
import com.example.rebrickable.models.Set


class SetDescriptionPageFragment(private val _set: Set) : Fragment() {
    private val _viewModel: SetDetailsViewModel by viewModels {
        ViewModelFactories.setDetailsViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSetDescriptionPageBinding.inflate(inflater)

        binding.setThemeTextView.text = _set.theme?.getFullThemeName() ?: "LEGO®"
        binding.setNameTextView.text = _set.name
        binding.setNumberTextView.text = _set.setNum
        val partCountText = resources.getQuantityString(
            R.plurals.set_part_count_full, _set.partCount, _set.partCount
        )
        binding.setPartCountTextView.text = partCountText
        binding.setYearTextView.text = _set.releaseYear.toString()

        _viewModel.loadSetMinifigs(_set).invokeOnCompletion {
            val minifigCount = _set.minifigs?.size ?: 0
            val minifigCountText = resources.getQuantityString(
                R.plurals.set_minifig_count, minifigCount, minifigCount
            )
            binding.setMinifigCountTextView.text = minifigCountText
        }

        binding.rebrickableUrlTextView.text = _set.rebrickableURL ?: ""

        return binding.root
    }
}
