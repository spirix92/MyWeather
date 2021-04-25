package com.selen.myweather.ui.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.selen.myweather.R
import com.selen.myweather.app.App

/**
 * Экран для отображения погоды
 * */
class SettingsFragment : Fragment() {

    private lateinit var settingsButton: Button

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

        settingsButton = view.findViewById(R.id.fragment_settings_button)

        viewModel.apply {
            dataLiveData.observeForever { myData(it) }
        }

        settingsButton.text = viewModel.count.toString()
        settingsButton.setOnClickListener { viewModel.addCount() }
    }

    fun myData(data: Int) {
        settingsButton.text = data.toString()
    }
}