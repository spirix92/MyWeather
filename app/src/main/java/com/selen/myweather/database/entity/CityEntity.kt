package com.selen.myweather.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Pyaterko Aleksey
 */
@Entity(tableName = "Cities")
data class CityEntity(

    @PrimaryKey
    @ColumnInfo(name = "city_name")
    var cityName: String
)