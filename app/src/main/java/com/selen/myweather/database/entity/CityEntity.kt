package com.selen.myweather.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Pyaterko Aleksey
 */
@Entity(tableName = "Cities")
class CityEntity {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "city_name")
    var cityName: String? = null
}