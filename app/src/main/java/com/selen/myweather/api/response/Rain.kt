package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class Rain(

    @Expose
    @SerializedName("3h")
    var threeH: Double

)