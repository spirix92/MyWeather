package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class Main(

    @Expose
    @SerializedName("temp")
    var temp: Double,

    @Expose
    @SerializedName("feels_like")
    var feelsLike: Double,

    @Expose
    @SerializedName("temp_min")
    var tempMin: Double,

    @Expose
    @SerializedName("temp_max")
    var tempMax: Double,

    @Expose
    @SerializedName("pressure")
    var pressure: Int,

    @Expose
    @SerializedName("sea_level")
    var seaLevel: Int,

    @Expose
    @SerializedName("grnd_level")
    var grndLevel: Int,

    @Expose
    @SerializedName("humidity")
    var humidity: Int,

    @Expose
    @SerializedName("temp_kf")
    var tempKf: Double

)