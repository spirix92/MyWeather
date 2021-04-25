package com.selen.myweather.database.repository

import android.content.Context
import android.util.Log
import com.selen.myweather.R
import com.selen.myweather.app.App
import com.selen.myweather.database.CitiesDao
import com.selen.myweather.database.mapper.CityMapper
import com.selen.myweather.model.CityDatabaseModel
import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * @author Pyaterko Aleksey
 *
 * репозиторий для работы с БД CitiesDatabase
 */
class CitiesRepository constructor(
    val context: Context,
    var citiesDao: CitiesDao
) {

    private val cityMapper = CityMapper()

    fun loadCitiesRX(): Flowable<List<CityDatabaseModel>> {
        return citiesDao.getAllCitiesRX()
            .doOnSubscribe {
//    заполнение списка городов в БД
//    т.к. приложение демонстрационное, список городов получаем из массива и заполняем в БД(как будто скачали из сети)
//        очистим список городов
                citiesDao.deleteAllCities()
                Log.d(App.LOG_TAG, "бд очистили")
//        генерируем список городов(получим из массива)
                val mutableCitiesList: MutableList<CityDatabaseModel> = mutableListOf()
                context.resources.getStringArray(R.array.city_names).toList()
                    .forEachIndexed { index, name ->
                        val model = CityDatabaseModel()
                        model.cityName = name
                        mutableCitiesList.add(model)
                    }
                Log.d(App.LOG_TAG, "в списке ${mutableCitiesList.size} городов")
//        записываем в БД
                citiesDao.insertListCities(cityMapper.modelListToEntityList(mutableCitiesList))
                Log.d(App.LOG_TAG, "в бд записали")
            }
            .map { cities ->
                cityMapper.entityListToModelList(cities)
            }
    }

    //        запись списка городов в БД
    fun addListCities(listCities: List<CityDatabaseModel?>?): Completable {
        return Completable.fromAction {
            citiesDao.insertListCities(cityMapper.modelListToEntityList(listCities))
        }
    }

    //        очистка списка городов в БД
    fun removeAllCities(): Completable {
        return Completable.fromAction {
            citiesDao.deleteAllCities()
        }
    }

}