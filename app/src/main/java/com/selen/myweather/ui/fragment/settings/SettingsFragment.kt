package com.selen.myweather.ui.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.selen.myweather.R

/**
 * Экран для отображения погоды
 * */
class SettingsFragment : Fragment() {

    private lateinit var settingsButton: Button

    private val viewModel: SettingsViewModel by lazy {
        ViewModelProvider(this).get(SettingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        settingsButton = view.findViewById(R.id.fragment_settings_button)

        viewModel.apply {
            data.observeForever { myData(it) }
        }

        settingsButton.text = viewModel.count.toString()
        settingsButton.setOnClickListener { viewModel.addCount() }

        super.onViewCreated(view, savedInstanceState)
    }

    fun myData(data: Int) {
        settingsButton.text = data.toString()
    }
}