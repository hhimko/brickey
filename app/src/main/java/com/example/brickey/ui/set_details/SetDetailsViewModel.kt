package com.example.brickey.ui.set_details

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.rebrickable.RebrickableApiClient
import com.example.rebrickable.models.Minifig
import com.example.rebrickable.models.Set
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class SetDetailsViewModel(
    private val _apiClient: RebrickableApiClient
) : ViewModel() {
    private val _minifigsLD = MutableLiveData<List<Minifig>>()
    val minifigsLiveData: LiveData<List<Minifig>> = _minifigsLD
    private val _minifigsCountLD = MutableLiveData<Int>()
    val minifigsCountLiveData: LiveData<Int> = _minifigsCountLD


    fun loadSetImage(context: Context, view: ImageView, set: Set) {
        if (set.imageURL == null)
            return

        Glide.with(context).load(set.imageURL).into(view)
    }

    fun loadSetMinifigs(set: Set): Job {
        return viewModelScope.launch {
            if (set.minifigs == null)
                set.minifigs = _apiClient.getMinifigsForSetNum(set.setNum)

            _minifigsLD.value = set.minifigs
            _minifigsCountLD.value = set.minifigs?.size
        }
    }

    fun loadMinifigImage(context: Context, view: ImageView, minifig: Minifig) {
        if (minifig.imageURL == null)
            return

        Glide.with(context).load(minifig.imageURL).into(view)
    }
}