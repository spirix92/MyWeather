package com.selen.myweather.ui.fragment.map.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.selen.myweather.R
import com.selen.myweather.model.CityDatabaseModel

/**
 * @author Pyaterko Aleksey
 */
class MapAddressAdapter : RecyclerView.Adapter<MapAddressAdapter.MapAddressViewHolder>() {

    private var items = mutableListOf<CityDatabaseModel>()

    fun setData(list: List<CityDatabaseModel>) {
        items = list.toMutableList()
        items.sortBy { it.cityName }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MapAddressViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_map_address, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MapAddressViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MapAddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var address = view.findViewById<TextView>(R.id.item_map_address_text_view_address)

        fun bind(item: CityDatabaseModel) {
            address.text = item.cityName
        }

    }

}