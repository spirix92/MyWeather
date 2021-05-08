package com.selen.myweather.ui.fragment.weather.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.selen.myweather.R

/**
 * @author Pyaterko Aleksey
 */
class NewsAdapter(private val context: Context) : PagerAdapter() {

    var news: List<String> = listOf()

    override fun getCount(): Int {
        return news.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_news_fragment_weather, container, false)
        val text = view.findViewById<TextView>(R.id.item_news_fragment_weather_card_view_news)

        text.text = news[position]
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}