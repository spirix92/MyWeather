package com.selen.myweather.database.repository

import com.selen.myweather.database.CitiesDao
import com.selen.myweather.database.CitiesDatabase
import com.selen.myweather.database.entity.CityEntity
import com.selen.myweather.database.mapper.CityMapper
import com.selen.myweather.model.CityDatabaseModel
import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * @author Pyaterko Aleksey
 *
 * репозиторий для работы с БД CitiesDatabase
 */
class CitiesRepository constructor(citiesDatabase: CitiesDatabase) {

    private var citiesDao: CitiesDao = citiesDatabase.getCitiesDao()

    private val cityMapper = CityMapper()

    fun loadCitiesRX(): Flowable<List<CityDatabaseModel>> {
        return citiesDao.getAllCitiesRX()
            .map { cities ->
                cityMapper.entityListToModelList(cities)
            }
    }

    fun addListCities(listCities: List<CityDatabaseModel?>?): Completable {
        return Completable.fromAction {
            citiesDao.insertListCities(cityMapper.modelListToEntityList(listCities))
        }
    }

    fun removeAllCities(): Completable {
        return Completable.fromAction {
            citiesDao.deleteAllCities()
        }
    }

}