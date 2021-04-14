package com.selen.myweather.ui.fragment.map

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selen.myweather.app.App
import com.selen.myweather.model.CityDatabaseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Pyaterko Aleksey
 */
class MapViewModel : ViewModel() {

    /**
     * контекст передаётся только для эмуляции загрузки по сети
     */
    private val context = App.instance.applicationContext

    private val citiesRepository = App.instance.getCitiesRepository()

    val addressLiveData = MutableLiveData<List<CityDatabaseModel>>()

    init {
        loadAddress(context)
    }

    /**
     * эмулируем загрузку списка адресов в БД(как будто получили через API) в том случае, если при вызове передан context,
     * после чего получаем данные из БД и прогоняем через mapper. После этого упорядочиваем полученный список
     */
    fun loadAddress(context: Context? = null, filterAddress: String = "") {
        citiesRepository.loadCitiesRX(context)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cities ->
                addressLiveData.value = cities.filter {
                    it.cityName.toLowerCase().contains(filterAddress.toLowerCase())
                }
            }
    }

}