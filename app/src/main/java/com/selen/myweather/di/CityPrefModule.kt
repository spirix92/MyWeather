package com.selen.myweather.di

import android.content.Context
import com.selen.myweather.ui.fragment.map.pref.CityPref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Pyaterko Aleksey
 */
@Module
class CityPrefModule {

    @Singleton
    @Provides
    fun initCityPref(context: Context) = CityPref(context)
}