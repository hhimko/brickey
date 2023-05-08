package com.example.brickey.ui.set_details.minifigs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.brickey.R
import com.example.brickey.databinding.FragmentSetMinifigsPageBinding
import com.example.brickey.ui.set_details.SetDetailsViewModel
import com.example.rebrickable.models.Set


class SetMinifigsPageFragment(private val _viewModel: SetDetailsViewModel, private val _set: Set) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSetMinifigsPageBinding.inflate(inflater)

        setupMinifigsRecyclerView(binding)
        return binding.root
    }

    private fun setupMinifigsRecyclerView(binding: FragmentSetMinifigsPageBinding) {
        binding.minifigsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.minifigsRecyclerView.adapter = SetMinifigsAdapter(_viewModel, _viewModel.minifigsLiveData.value ?: emptyList())

        _viewModel.minifigsLiveData.observe(viewLifecycleOwner) {
            binding.minifigsRecyclerView.adapter = SetMinifigsAdapter(_viewModel, it)
        }

        _viewModel.minifigsCountLiveData.observe(viewLifecycleOwner) {
            val text = resources.getQuantityString(R.plurals.set_minifig_count_full, it, it)
            binding.setMinifigsCountTotalTextView.text = text
        }
    }
}