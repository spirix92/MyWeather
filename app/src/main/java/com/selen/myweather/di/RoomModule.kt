package com.selen.myweather.di

import android.content.Context
import androidx.room.Room
import com.selen.myweather.database.CitiesDao
import com.selen.myweather.database.CitiesDatabase
import com.selen.myweather.database.repository.CitiesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Pyaterko Aleksey
 */
@Module
class RoomModule {

    @Named("databaseName")
    @Provides
    fun databaseName(): String {
        return "cities_database"
    }

    @Singleton
    @Provides
    fun initRoom(@Named("databaseName") databaseName: String, context: Context): CitiesDatabase =
        Room.databaseBuilder(context, CitiesDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun initDao(citiesDatabase: CitiesDatabase): CitiesDao = citiesDatabase.getCitiesDao()

    @Singleton
    @Provides
    fun initRoomRepository(context: Context, citiesDao: CitiesDao): CitiesRepository =
        CitiesRepository(context, citiesDao)

}