package com.selen.myweather.database.repository

import com.selen.myweather.database.CitiesDao
import com.selen.myweather.database.CitiesDatabase
import com.selen.myweather.database.entity.CityEntity
import com.selen.myweather.database.mapper.CityMapper
import com.selen.myweather.database.model.CityModel


/**
 * @author Pyaterko Aleksey
 *
 * репозиторий для работы с БД CitiesDatabase
 */
class CitiesRepository constructor(citiesDatabase: CitiesDatabase){

    private var citiesDao: CitiesDao = citiesDatabase.getCitiesDao()

    private val cityMapper = CityMapper()

    private var cities: List<CityEntity?>? = null

    fun getCities(): List<CityModel?>? {
        if (cities == null) {
            loadCities()
        }
        return cityMapper.entityListToModelList(cities)
    }

    fun loadCities() {
        cities = citiesDao.getAllCities()
    }

    fun getCountCities(): Long {
        return citiesDao.getCountCities()
    }

    fun addListCities(listCities: List<CityModel?>?) {
        citiesDao.insertListCities(cityMapper.modelListToEntityList(listCities))
        loadCities()
    }

    fun addCities(city: CityModel?) {
        citiesDao.insertCity(cityMapper.modelToEntity(city))
        loadCities()
    }

    fun updateCities(city: CityModel?) {
        citiesDao.updateCity(cityMapper.modelToEntity(city))
        loadCities()
    }

    fun removeAllCities() {
        citiesDao.deleteAllCities()
        loadCities()
    }

    fun removeCity(city: CityModel?) {
        citiesDao.deleteCity(cityMapper.modelToEntity(city))
        loadCities()
    }

}