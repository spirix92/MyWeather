package com.selen.myweather.di

import com.selen.myweather.ui.activity.MainActivity
import com.selen.myweather.ui.activity.MainActivityViewModel
import com.selen.myweather.ui.fragment.map.MapFragment
import com.selen.myweather.ui.fragment.map.MapViewModel
import com.selen.myweather.ui.fragment.settings.SettingsFragment
import com.selen.myweather.ui.fragment.settings.SettingsViewModel
import com.selen.myweather.ui.fragment.weather.WeatherFragment
import com.selen.myweather.ui.fragment.weather.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * @author Pyaterko Aleksey
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        RoomModule::class,
        CityPrefModule::class,
        TemperatureUnitsPrefModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun inject(mapFragment: MapFragment)
    fun inject(mapViewModel: MapViewModel)

    fun inject(settingsFragment: SettingsFragment)
    fun inject(settingsViewModel: SettingsViewModel)

    fun inject(weatherFragment: WeatherFragment)
    fun inject(weatherViewModel: WeatherViewModel)
}