package com.selen.myweather.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.selen.myweather.R
import com.selen.myweather.api.DefaultApiWorker
import com.selen.myweather.api.WeatherApi
import com.selen.myweather.database.CitiesDao
import com.selen.myweather.database.CitiesDatabase
import com.selen.myweather.database.repository.CitiesRepository
import com.selen.myweather.model.CityDatabaseModel
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author Pyaterko Aleksey
 */
class App : Application() {

    private lateinit var context: Context

    private lateinit var citiesDatabase: CitiesDatabase

    private lateinit var citiesDao: CitiesDao

    private lateinit var citiesRepository: CitiesRepository

    private lateinit var gson: Gson

    private lateinit var retrofit: Retrofit

    private lateinit var api: WeatherApi

    private lateinit var apiWeather: DefaultApiWorker

    override fun onCreate() {
        super.onCreate()

        instance = this

        context = applicationContext

        initCitiesDatabase()
        fillingTheTableCitiesRepository()

        initRetrofit()
    }

    //    инициализация базы данных с названиями городов
    private fun initCitiesDatabase() {
        citiesDatabase = Room.databaseBuilder(
            applicationContext,
            CitiesDatabase::class.java,
            "cities_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        citiesDao = citiesDatabase.getCitiesDao()
        citiesRepository = CitiesRepository(citiesDatabase)
    }

    //    заполнение списка городов в БД
    //    т.к. приложение демонстрационное, список городов получаем из массива и заполняем в БД(как будто скачали из сети)
    private fun fillingTheTableCitiesRepository() {
//        очищаем БД
        citiesRepository.removeAllCities()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.d(LOG_TAG, "бд очистили") },
                { error ->
                    Log.d(
                        LOG_TAG,
                        error.message ?: "пытались очистить БД, но что-то пошло не так"
                    )
                })
//        генерируем список городов
        val mutableCitiesList: MutableList<CityDatabaseModel> = mutableListOf()
        resources.getStringArray(R.array.cityName).toList().forEachIndexed { index, name ->
            val model = CityDatabaseModel()
            model.id = index.toLong()
            model.cityName = name
            mutableCitiesList.add(model)
        }
        Log.d(LOG_TAG, "в списке городов ${mutableCitiesList.size} штук")
//        записываем в БД
        citiesRepository.addListCities(mutableCitiesList)
            .subscribeOn(Schedulers.io())
            .subscribe(
                { Log.d(LOG_TAG, "в бд записали") },
                { error ->
                    Log.d(
                        LOG_TAG,
                        error.message ?: "пытались очистить БД, но что-то пошло не так"
                    )
                })
    }

    //    инициализация retrofit
    private fun initRetrofit() {
        gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(WeatherApi::class.java)

        apiWeather = DefaultApiWorker(api)
    }

    fun getApp() = instance

    fun getCitiesDao() = citiesDao

    fun getCitiesRepository() = citiesRepository

    fun getApi() = api

    companion object {
        val LOG_TAG = "LOG_TAG"
        lateinit var instance: App
    }
}