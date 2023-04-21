package com.example.brickey.ui.search_results

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brickey.R
import com.example.brickey.databinding.SearchResultCardviewBinding
import com.example.rebrickable.models.Set


class SearchResultsAdapter(
    private val _viewModel: SearchResultsViewModel, private val _results: List<Set>
) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {

    class SearchResultsViewHolder(
        private val _context: Context,
        private val _binding: SearchResultCardviewBinding
    ) : RecyclerView.ViewHolder(_binding.root) {

        fun bindSearchResult(viewModel: SearchResultsViewModel, set: Set) {
            viewModel.loadSetImage(_binding.root.context, _binding.setImageView, set)
            viewModel.loadBlurredSetImage(_binding.root.context, _binding.backgroundOverlayImageView, set)

            _binding.setNameTextView.text = set.name
            _binding.setNumberTextView.text = set.setNum
            val partCountText = _context.getString(R.string.set_part_count, set.partCount)
            _binding.setPartCountTextView.text = partCountText
            _binding.setYearTextView.text = set.releaseYear.toString()

            // Query the set theme info
            viewModel.loadSetTheme(set) {
                _binding.setThemeTextView.text = set.theme?.getFullThemeName() ?: "LEGO®"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultCardviewBinding.inflate(inflater, parent, false)

        return SearchResultsViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(viewHolder: SearchResultsViewHolder, position: Int) {
        val set = _results[position]
        viewHolder.bindSearchResult(_viewModel, set)
    }

    override fun getItemCount(): Int {
        return _results.size
    }
}
