package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class Coord(

    @Expose
    @SerializedName("lat")
    var lat: Double,

    @Expose
    @SerializedName("lon")
    var lon: Double

)