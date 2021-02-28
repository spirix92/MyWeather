package com.selen.myweather.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selen.myweather.database.entity.CityEntity

/**
 * @author Pyaterko Aleksey
 */
@Database(entities = [CityEntity::class], version = 1, exportSchema = false)
abstract class CitiesDatabase : RoomDatabase() {
    abstract fun getCitiesDao(): CitiesDao
}