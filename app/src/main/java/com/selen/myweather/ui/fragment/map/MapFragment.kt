package com.selen.myweather.ui.fragment.map

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.selen.myweather.R
import com.selen.myweather.model.CityDatabaseModel
import com.selen.myweather.ui.fragment.map.adapter.MapAddressAdapter

/**
 * @author Pyaterko Aleksey
 */
class MapFragment : Fragment() {

    private lateinit var searchInputLayout: TextInputLayout
    private lateinit var searchInputEditText: TextInputEditText
    private lateinit var addressRecycler: RecyclerView
    private lateinit var addressAdapter: MapAddressAdapter

    //    TextWatcher для работы поисковой строки
    private var addressTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {
            viewModel.loadAddress(s.toString())
        }
    }

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

        initViews(view)

        viewModel.apply {
            addressLiveData.observeForever { setAddress(it) }
        }

        addressAdapter = MapAddressAdapter()

        addressRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = addressAdapter
        }

        searchInputEditText.addTextChangedListener(addressTextWatcher)
    }

    fun initViews(view: View) {
        searchInputLayout = view.findViewById(R.id.fragment_map_text_input_layout_search)
        searchInputEditText = view.findViewById(R.id.fragment_map_text_input_edit_text_search)
        addressRecycler = view.findViewById(R.id.fragment_map_recycler_view_cities)
    }

    fun setAddress(cities: List<CityDatabaseModel>) {
        addressAdapter.setData(cities)
    }

}