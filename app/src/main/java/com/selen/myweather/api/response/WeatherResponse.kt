package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class WeatherResponse(

    @Expose
    @SerializedName("cod")
    var cod: String,

    @Expose
    @SerializedName("message")
    var message: Int,

    @Expose
    @SerializedName("cnt")
    var cnt: Int,

    @Expose
    @SerializedName("list")
    var list: List<WeatherList>,

    @Expose
    @SerializedName("city")
    var city: City

)