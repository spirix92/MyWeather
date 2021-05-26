package com.selen.myweather.ui.fragment.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.selen.myweather.R
import com.selen.myweather.api.response.WeatherList
import com.selen.myweather.helper.DateParser

/**
 * @author Pyaterko Aleksey
 */
class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var items = mutableListOf<WeatherList>()
    private var dateMapper = DateParser()
    private var tempUnit: String = ""

    fun setData(list: List<WeatherList>, tempUnit: String) {
        items = list.toMutableList()
        this.tempUnit = tempUnit
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_fragment_weather, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class WeatherViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var date =
            view.findViewById<TextView>(R.id.item_weather_fragment_weather_text_view_date)
        private var time =
            view.findViewById<TextView>(R.id.item_weather_fragment_weather_text_view_time)
        private var temp =
            view.findViewById<TextView>(R.id.item_weather_fragment_weather_text_view_temp)
        private var tempFeelLike =
            view.findViewById<TextView>(R.id.item_weather_fragment_weather_text_view_temp_feel_like)
        private var imageWeather =
            view.findViewById<ImageView>(R.id.item_weather_fragment_weather_image_view_weather)

        fun bind(item: WeatherList) {
            time.text = dateMapper.getDate(item.dtTxt)
            date.text = dateMapper.getTime(item.dtTxt)
            temp.text = "${item.main.temp.toInt()}$tempUnit"
            tempFeelLike.text = "${item.main.feelsLike.toInt()}$tempUnit"

            Glide
                .with(imageWeather)
                .load(
                    imageWeather.context.getString(
                        R.string.icon_weather_url,
                        item.weather.first().icon
                    )
                )
                .into(imageWeather)

//            скорость ветра
            item.wind.speed

//            item.main.humidity влажность
            item.weather.first().id

//            группа погоды(дождь снег и тд)
            item.weather.first().main

//            погодные условия в группе
            item.weather.first().description

//            идентификатор иконки
            item.weather.first().icon

        }
    }
}