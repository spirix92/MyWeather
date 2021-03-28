package com.selen.myweather.api.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Pyaterko Aleksey
 */
data class City(

    @Expose
    @SerializedName("id")
    var id: Int,

    @Expose
    @SerializedName("name")
    var name: String,

    @Expose
    @SerializedName("coord")
    var coord: Coord,

    @Expose
    @SerializedName("country")
    var country: String,

    @Expose
    @SerializedName("timezone")
    var timezone: Int,

    @Expose
    @SerializedName("sunrise")
    var sunrise: Int,

    @Expose
    @SerializedName("sunset")
    var sunset: Int

)