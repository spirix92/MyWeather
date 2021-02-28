package com.selen.myweather.ui.fragment.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.selen.myweather.R
import com.selen.myweather.app.App
import com.selen.myweather.database.model.CityModel

/**
 * @author Pyaterko Aleksey
 */
class MapFragment : Fragment() {

    private val viewModel: MapViewModel by lazy {
        ViewModelProvider(this).get(MapViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val citiesRepository = App.instance.getCitiesRepository()
        Toast.makeText(requireContext(), "${citiesRepository.getCountCities()} штук", Toast.LENGTH_SHORT).show()

    }
}