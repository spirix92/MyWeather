package com.selen.myweather.database

import androidx.room.*
import com.selen.myweather.database.entity.CityEntity

/**
 * @author Pyaterko Aleksey
 */
@Dao
interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListCities(listCities: List<CityEntity?>?)

    @Update
    fun updateCity(city: CityEntity?)

    @Delete
    fun deleteCity(city: CityEntity?)

    @Query("DELETE FROM Cities")
    fun deleteAllCities()

    @Query("DELETE FROM Cities WHERE `city_name` = :cityName")
    fun deleteCityByCityName(cityName: String?)

    @Query("SELECT * FROM Cities")
    fun getAllCities(): List<CityEntity?>?

    @Query("SELECT * FROM Cities WHERE `city_name` = :cityName")
    fun getCityByCityName(cityName: String?): CityEntity?

    @Query("SELECT COUNT() FROM Cities")
    fun getCountCities(): Long
}