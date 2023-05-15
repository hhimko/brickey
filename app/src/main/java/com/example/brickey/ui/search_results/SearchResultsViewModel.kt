package com.example.brickey.ui.search_results

import android.content.Context
import android.view.ViewTreeObserver.OnPreDrawListener
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.request.RequestOptions
import com.example.brickey.models.SetSearchQuery
import com.example.rebrickable.RebrickableApiClient
import kotlinx.coroutines.launch
import com.example.rebrickable.models.Set
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropTransformation
import kotlinx.coroutines.Job


class SearchResultsViewModel(
    private val _apiClient: RebrickableApiClient
) : ViewModel() {
    private val _setsLD = MutableLiveData<List<Set>>()
    val setsLiveData: LiveData<List<Set>> = _setsLD
    private val _setsCountLD = MutableLiveData<Int>()
    val setsCountLiveData: LiveData<Int> = _setsCountLD


    fun searchSets(query: SetSearchQuery) {
        viewModelScope.launch {
            val pagedResponse = _apiClient.getSetsAsync(query.searchTerm, query.releaseYear)
            _setsLD.value = pagedResponse?.results ?: emptyList()
            _setsCountLD.value = pagedResponse?.resultsCount ?: 0
        }
    }

    fun loadSetTheme(set: Set): Job {
        return viewModelScope.launch {
            if (set.theme != null)
                return@launch

            set.theme = _apiClient.getThemeByIdAsync(set.themeId)
        }
    }

    fun loadSetImage(context: Context, view: ImageView, set: Set) {
        if (set.imageURL == null)
            return

        Glide.with(context).load(set.imageURL).centerCrop().into(view)
    }

    fun loadBlurredSetImage(context: Context, view: ImageView, set: Set) {
        if (set.imageURL == null)
            return

        val viewTreeObserver = view.viewTreeObserver
        if (viewTreeObserver.isAlive) {
            viewTreeObserver.addOnPreDrawListener(object: OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    view.viewTreeObserver.removeOnPreDrawListener(this)

                    val transform = MultiTransformation(
                        BlurTransformation(10, 3),
                        CropTransformation(view.width, view.height, CropTransformation.CropType.CENTER)
                    )

                    Glide.with(context).load(set.imageURL).apply(RequestOptions.bitmapTransform(transform)).into(view)
                    return true
                }
            })
        }
    }
}
