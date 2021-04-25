package com.selen.myweather.ui.fragment.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selen.myweather.database.repository.CitiesRepository
import com.selen.myweather.model.CityDatabaseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Pyaterko Aleksey
 */
class MapViewModel : ViewModel() {

    @Inject
    lateinit var citiesRepository: CitiesRepository

    val addressLiveData = MutableLiveData<List<CityDatabaseModel>>()

    /**
     * эмулируем загрузку списка адресов в БД(как будто получили через API)
     * после чего получаем данные из БД и прогоняем через mapper. После этого упорядочиваем полученный список
     */
    fun loadAddress(filterAddress: String = "") {
        citiesRepository.loadCitiesRX()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cities ->
                addressLiveData.value = cities.filter {
                    it.cityName.toLowerCase().contains(filterAddress.toLowerCase())
                }
            }
    }

}