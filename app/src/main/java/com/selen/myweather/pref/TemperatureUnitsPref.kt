package com.selen.myweather.pref

import android.content.Context
import com.selen.myweather.type.TemperatureUnitsType

/**
 * @author Pyaterko Aleksey
 */
class TemperatureUnitsPref(context: Context) {

    private val prefs = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)
    private val defaultUnit: TemperatureUnitsType = TemperatureUnitsType.CELSIUS

    //    город для которого будет показываться погода
    var currentUnitSelected: TemperatureUnitsType
        get() {
            val name = prefs.getString(CURRENT_UNIT_SELECTED, defaultUnit.name) ?: defaultUnit.name
            return TemperatureUnitsType.valueOf(name)
        }
        set(city) = prefs.edit().putString(CURRENT_UNIT_SELECTED, city.name).apply()

    companion object {
        private const val SHARED_PREFS_FILE_NAME = "TemperatureUnitsPref"
        private const val CURRENT_UNIT_SELECTED = "CURRENT_UNIT_SELECTED"
    }

}