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
            binding.setThemeTextView.text = set.theme?.getFullThemeName() ?: "LEGO®"
            binding.setNameTextView.text = set.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultCardviewBinding.inflate(inflater, parent, false)

        return SearchResultsViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: SearchResultsViewHolder, position: Int) {
        val set = _results[position]

        // Query the set theme info
        _viewModel.loadSetTheme(set)

        viewHolder.bindSearchResult(_viewModel, set)
    }

    override fun getItemCount(): Int {
        return _results.size
    }
}
