package com.selen.myweather.ui.fragment.settings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Pyaterko Aleksey
 */
class SettingsViewModel : ViewModel() {

    var count: Int = 0
    val dataLiveData = MutableLiveData<Int>()

    fun addCount() {
        dataLiveData.value = ++count
    }
}