package com.selen.myweather.di

import android.content.Context
import com.selen.myweather.pref.TemperatureUnitsPref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Pyaterko Aleksey
 */
@Module
class TemperatureUnitsPrefModule {

    @Singleton
    @Provides
    fun initUnitsPref(context: Context) = TemperatureUnitsPref(context)
}