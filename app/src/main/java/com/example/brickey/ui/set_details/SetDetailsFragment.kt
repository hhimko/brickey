package com.example.brickey.ui.set_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.brickey.databinding.FragmentSetDetailsBinding
import com.example.brickey.di.ViewModelFactories
import com.example.rebrickable.models.Set


class SetDetailsFragment : Fragment() {
    private val _navArgs: SetDetailsFragmentArgs by navArgs()
    private lateinit var _set: Set
    private val _viewModel: SetDetailsViewModel by viewModels {
        ViewModelFactories.setDetailsViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSetDetailsBinding.inflate(inflater)
        _set = _navArgs.set

        _viewModel.loadSetImage(binding.root.context, binding.setImageView, _set)

        binding.navigateBackButton.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }

        setupViewPager(binding)
        return binding.root
    }

    private fun setupViewPager(binding: FragmentSetDetailsBinding) {
        val viewPager = binding.contentViewPager
        val chipGroup = binding.contentChipGroup

        viewPager.adapter = SetDetailsStateAdapter(this, _viewModel, _set)
        viewPager.registerOnPageChangeCallback(object: OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val chip = if (position == SetDetailsStateAdapter.Pages.DESCRIPTION_PAGE.value)
                    binding.contentChipDescription else binding.contentChipMinifigures
                chip.isChecked = true
            }
        })

        chipGroup.isSelectionRequired = true
        chipGroup.isSingleSelection = true
        chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            val position = if (checkedIds[0] == binding.contentChipDescription.id) 0 else 1
            viewPager.setCurrentItem(position, true)
        }

        binding.contentChipDescription.isChecked = true
    }
}
