package com.selen.myweather.ui.fragment.settings

import androidx.lifecycle.ViewModel
import com.selen.myweather.pref.TemperatureUnitsPref
import com.selen.myweather.type.TemperatureUnitsType
import javax.inject.Inject

/**
 * @author Pyaterko Aleksey
 */
class SettingsViewModel : ViewModel() {

    @Inject
    lateinit var currentTemperatureUnitsData: TemperatureUnitsPref

    fun getCurrentTemperatureUnit(): TemperatureUnitsType {
        return currentTemperatureUnitsData.currentUnitSelected
    }

    fun setTemperatureUnit(unit: TemperatureUnitsType) {
        currentTemperatureUnitsData.currentUnitSelected = unit
    }

}