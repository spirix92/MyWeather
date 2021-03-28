package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class Wind(

    @Expose
    @SerializedName("speed")
    var speed: Double,

    @Expose
    @SerializedName("deg")
    var deg: Int

)