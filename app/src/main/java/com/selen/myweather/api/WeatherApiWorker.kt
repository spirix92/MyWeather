package com.selen.myweather.api

import com.selen.myweather.api.response.WeatherResponse
import io.reactivex.Single

/**
 * @author Pyaterko Aleksey
 */
interface WeatherApiWorker {

    fun loadWeather(
        cityCountry: String,
        units: String,
        keyApi: String,
        lang: String
    ): Single<WeatherResponse>
}