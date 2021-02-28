package com.selen.myweather.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Pyaterko Aleksey
 */
class CityModel {
    var id: Long = 0
    var cityName: String? = null
}