package com.example.brickey.ui.search_results

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brickey.databinding.SearchResultCardviewBinding
import com.example.rebrickable.models.Set


class SearchResultsAdapter(private val _results: List<Set>) :
    RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {

    class SearchResultsViewHolder(context: Context, val binding: SearchResultCardviewBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bindSearchResult(set: Set) {
            binding.textView.text = set.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchResultCardviewBinding.inflate(inflater, parent, false)

        return SearchResultsViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(viewHolder: SearchResultsViewHolder, position: Int) {
        viewHolder.bindSearchResult(_results[position])
    }

    override fun getItemCount(): Int {
        return _results.size
    }
}
