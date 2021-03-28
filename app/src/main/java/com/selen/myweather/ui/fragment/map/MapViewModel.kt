package com.selen.myweather.ui.fragment.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selen.myweather.app.App
import com.selen.myweather.model.CityDatabaseModel
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * @author Pyaterko Aleksey
 */
class MapViewModel : ViewModel() {

    private val citiesRepository = App.instance.getCitiesRepository()

    val addressLiveData = MutableLiveData<List<CityDatabaseModel>>()

    init {
        loadAddress()
    }

    //    загружает адреса из БД, после чего фильтрует(на случай, если вызвали через поиск)
    fun loadAddress(filterAddress: String = "") {

        citiesRepository.loadCitiesRX()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cities ->
                addressLiveData.value = cities.filter {
                    it.cityName.toLowerCase().contains(filterAddress.toLowerCase())
                }
            }

    }

}