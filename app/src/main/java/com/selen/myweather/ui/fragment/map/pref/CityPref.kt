package com.selen.myweather.ui.fragment.map.pref

import android.content.Context
import com.selen.myweather.R
import com.selen.myweather.app.App

/**
 * @author Pyaterko Aleksey
 */
class CityPref {

    val context = App.instance

    private val prefs = context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_PRIVATE)
    private val defaultCity: String = context.getString(R.string.default_city)

    //    город для которого будет показываться погода
    var currentCitySelected: String
        get() = prefs.getString(CURRENT_CITY_SELECTED, defaultCity) ?: defaultCity
        set(city) = prefs.edit().putString(CURRENT_CITY_SELECTED, city).apply()

    companion object {
        private const val SHARED_PREFS_FILE_NAME = "CityPrefs"
        private const val CURRENT_CITY_SELECTED = "CURRENT_CITY_SELECTED"
    }

}