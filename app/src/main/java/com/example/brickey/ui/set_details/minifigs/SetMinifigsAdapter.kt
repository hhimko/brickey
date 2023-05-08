package com.example.brickey.ui.set_details.minifigs

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brickey.R
import com.example.brickey.databinding.SetMinifigsCardviewBinding
import com.example.brickey.ui.set_details.SetDetailsViewModel
import com.example.rebrickable.models.Minifig


class SetMinifigsAdapter(
    private val _viewModel: SetDetailsViewModel,
    private val _minifigs: List<Minifig>,
) : RecyclerView.Adapter<SetMinifigsAdapter.SetMinifigsViewHolder>() {

    class SetMinifigsViewHolder(
        private val _context: Context,
        private val _binding: SetMinifigsCardviewBinding
    ) : RecyclerView.ViewHolder(_binding.root) {

        fun bindMinifig(viewModel: SetDetailsViewModel, minifig: Minifig) {
            viewModel.loadMinifigImage(_binding.root.context, _binding.minifigImageView, minifig)

            _binding.minifigNameTextView.text = minifig.name
            val partCountText = _context.resources.getQuantityString(R.plurals.set_part_count_full, minifig.partsCount, minifig.partsCount)
            _binding.minifigPieceCountTextView.text = partCountText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetMinifigsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SetMinifigsCardviewBinding.inflate(inflater, parent, false)

        return SetMinifigsViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(viewHolder: SetMinifigsViewHolder, position: Int) {
        val minifig = _minifigs[position]
        viewHolder.bindMinifig(_viewModel, minifig)
    }

    override fun getItemCount(): Int {
        return _minifigs.size
    }
}
