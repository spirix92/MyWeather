package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class WeatherList(

    @Expose
    @SerializedName("dt")
    var dt: Int,

    @Expose
    @SerializedName("main")
    var main: Main,

    @Expose
    @SerializedName("weather")
    var weather: List<Weather>,

    @Expose
    @SerializedName("clouds")
    var clouds: Clouds,

    @Expose
    @SerializedName("wind")
    var wind: Wind,

    @Expose
    @SerializedName("visibility")
    var visibility: Int,

    @Expose
    @SerializedName("pop")
    var pop: Double,

    @Expose
    @SerializedName("rain")
    var rain: Rain,

    @Expose
    @SerializedName("sys")
    var sys: Sys,

    @Expose
    @SerializedName("dt_txt")
    var dtTxt: String

)