package com.selen.myweather.ui.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.selen.myweather.R
import com.selen.myweather.app.App
import com.selen.myweather.type.TemperatureUnitsType

/**
 * Экран для отображения погоды
 * */
class SettingsFragment : Fragment() {

    private lateinit var temperatureUnitsTabLayout: TabLayout

    private val viewModel: SettingsViewModel by lazy {
        ViewModelProvider(this).get(SettingsViewModel::class.java).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.appComponent.inject(this)

        initViews(view)

        viewModel.apply {
        }

    }

    fun initViews(view: View) {
        temperatureUnitsTabLayout =
            view.findViewById(R.id.fragment_settings_tab_layout_temperature_units)
        temperatureUnitsTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.setTemperatureUnit(TemperatureUnitsType.valueOf(tab.position))
            }
        })
        temperatureUnitsTabLayout.getTabAt(viewModel.getCurrentTemperatureUnit().position)?.select()
    }

}