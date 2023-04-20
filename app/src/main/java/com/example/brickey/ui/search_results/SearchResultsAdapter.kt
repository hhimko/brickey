package com.example.brickey.ui.search_results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brickey.databinding.SearchResultCardviewBinding
import com.example.rebrickable.models.Set


class SearchResultsAdapter(
    private val _viewModel: SearchResultsViewModel, private val _results: List<Set>
) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {

    class SearchResultsViewHolder(private val binding: SearchResultCardviewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindSearchResult(viewModel: SearchResultsViewModel, set: Set) {
            viewModel.loadSetImage(binding.root.context, binding.setImageView, set)
            viewModel.loadBlurredSetImage(binding.root.context, binding.backgroundOverlayImageView, set)

            binding.setNameTextView.text = set.name
            binding.setNumberTextView.text = set.setNum
            binding.setYearTextView.text = set.releaseYear.toString()

            // Query the set theme info
            viewModel.loadSetTheme(set) {
                binding.setThemeTextView.text = set.theme?.getFullThemeName() ?: "LEGO®"
            }

            // Query the set minifig list
//            viewModel.loadSetMinifigs(set) {
//                binding.minifigCountTextView.text =
//                    if (set.minifigs != null) set.minifigs!!.count().toString() else "?"
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultCardviewBinding.inflate(inflater, parent, false)

        return SearchResultsViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SearchResultsViewHolder, position: Int) {
        val set = _results[position]
        viewHolder.bindSearchResult(_viewModel, set)
    }

    override fun getItemCount(): Int {
        return _results.size
    }
}
