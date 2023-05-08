package com.example.brickey.ui.set_details

import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.brickey.ui.set_details.minifigs.SetMinifigsPageFragment
import com.example.rebrickable.models.Set

const val NUM_PAGES = 2


class SetDetailsStateAdapter(fragment: Fragment, private val _viewModel: SetDetailsViewModel, private val _set: Set)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        val fragment = when(position) {
            Pages.DESCRIPTION_PAGE.value -> SetDescriptionPageFragment(_viewModel, _set)
            Pages.MINIFIGS_PAGE.value -> SetMinifigsPageFragment(_viewModel, _set)
            else -> error("Undefined fragment at position $position")
        }
        return fragment
    }

    enum class Pages(val value: Int) {
        DESCRIPTION_PAGE(0),
        MINIFIGS_PAGE(1)
    }
}
