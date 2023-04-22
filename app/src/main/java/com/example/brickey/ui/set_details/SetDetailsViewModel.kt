package com.example.brickey.ui.set_details

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.rebrickable.RebrickableApiClient
import com.example.rebrickable.models.Set
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SetDetailsViewModel(
    private val _apiClient: RebrickableApiClient
) : ViewModel() {

    fun loadSetImage(context: Context, view: ImageView, set: Set) {
        if (set.imageURL == null)
            return

        Glide.with(context).load(set.imageURL).into(view)
    }

    fun loadSetMinifigs(set: Set): Job {
        return viewModelScope.launch {
            if (set.minifigs != null)
                return@launch

            set.minifigs = _apiClient.getMinifigsForSetNum(set.setNum)
        }
    }
}