package com.selen.myweather.ui.fragment.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Pyaterko Aleksey
 */
class SettingsViewModel : ViewModel() {

    var count: Int = 0
    val data = MutableLiveData<Int>()

    fun addCount() {
        data.value = ++count
    }
}