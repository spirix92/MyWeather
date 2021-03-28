package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class Weather(

    @Expose
    @SerializedName("id")
    var id: Int,

    @Expose
    @SerializedName("main")
    var main: String,

    @Expose
    @SerializedName("description")
    var description: String,

    @Expose
    @SerializedName("icon")
    var icon: String

)