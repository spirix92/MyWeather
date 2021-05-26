package com.selen.myweather.type

import com.selen.myweather.R

/**
 * @author Pyaterko Aleksey
 */
enum class TemperatureUnitsType(val position: Int, val unit: String, val characterResource: Int) {
    CELSIUS(0, "metric", R.string.celsius),
    FAHRENHEIT(1, "imperial", R.string.fahrenheit),
    KELVIN(2, "standard", R.string.kelvin);

    companion object {
        fun valueOf(position: Int): TemperatureUnitsType {
            return values().firstOrNull { it.position == position } ?: CELSIUS
        }
    }
}