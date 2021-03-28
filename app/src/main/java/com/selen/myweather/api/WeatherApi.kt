package com.selen.myweather.api

import com.selen.myweather.api.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Pyaterko Aleksey
 */
interface WeatherApi {

    @GET("data/2.5/forecast")
    fun loadWeather(
        @Query("q") cityCountry: String,
        @Query("units") units: String,
        @Query("appid") keyApi: String
    ): Single<WeatherResponse>

}