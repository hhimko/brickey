package com.example.brickey.ui.home

import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import androidx.lifecycle.ViewModel


class HomeViewModel: ViewModel() {
    fun getShakeAnimation(): TranslateAnimation {
        val shake = TranslateAnimation(0f, 10f, 0f, 0f)
        shake.duration = 800
        shake.interpolator = CycleInterpolator(6f)
        return shake
    }
}
