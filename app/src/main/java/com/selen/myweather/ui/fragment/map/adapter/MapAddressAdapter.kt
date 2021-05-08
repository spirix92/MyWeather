package com.selen.myweather.ui.fragment.map.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.selen.myweather.R
import com.selen.myweather.model.CityDatabaseModel

/**
 * @author Pyaterko Aleksey
 */
class MapAddressAdapter : RecyclerView.Adapter<MapAddressAdapter.MapAddressViewHolder>() {

    private var items = mutableListOf<CityDatabaseModel>()

    var onCityClick: ((String) -> Unit)? = null

    fun setData(list: List<CityDatabaseModel>) {
        items = list.toMutableList()
        items.sortBy { it.cityName }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MapAddressViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_address_fragment_map, parent, false)
    )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MapAddressViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MapAddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var addressContainer =
            view.findViewById<ConstraintLayout>(R.id.item_map_constraint_layout_address)
        private var address = view.findViewById<TextView>(R.id.item_address_fragment_map_text_view_address)

        fun bind(item: CityDatabaseModel) {
            addressContainer.setOnClickListener {
                onCityClick?.invoke(item.cityName)
            }
            address.text = item.cityName
        }
    }

}