package com.selen.myweather.app

import android.app.Application
import androidx.room.Room
import com.selen.myweather.R
import com.selen.myweather.database.CitiesDao
import com.selen.myweather.database.CitiesDatabase
import com.selen.myweather.database.model.CityModel
import com.selen.myweather.database.repository.CitiesRepository


/**
 * @author Pyaterko Aleksey
 */
class App : Application() {

    private lateinit var citiesDatabase: CitiesDatabase

    private lateinit var citiesDao: CitiesDao

    private lateinit var citiesRepository: CitiesRepository

    override fun onCreate() {
        super.onCreate()

        instance = this

        initCitiesDatabase()
        fillingTheTableCitiesRepository()
    }

//    инициализация базы данных с названиями городов
// TODO: 28.02.2021 покачто работу с БД сделал в основном потоке
    private fun initCitiesDatabase() {
        citiesDatabase = Room.databaseBuilder(
            applicationContext,
            CitiesDatabase::class.java,
            "cities_database")
            .allowMainThreadQueries()
            .build()

        citiesDao = citiesDatabase.getCitiesDao()
        citiesRepository = CitiesRepository(citiesDatabase)
    }

//    заполнение списка городов в БД
//    т.к. приложение демонстрационное, список городов получаем из массива и заполняем в БД(как будто скачали из сети)
    private fun fillingTheTableCitiesRepository() {
        citiesRepository.removeAllCities()
        val mutableCitiesList: MutableList<CityModel> = mutableListOf()
        resources.getStringArray(R.array.cityName).toList().forEachIndexed { index, name ->
            val model = CityModel()
            model.id = index.toLong()
            model.cityName = name
            mutableCitiesList.add(model)
        }
        citiesRepository.addListCities(mutableCitiesList)
    }

    fun getApp() = instance

    fun getCitiesDao() = citiesDao

    fun getCitiesRepository() = citiesRepository

    companion object {
        lateinit var instance: App
    }
}