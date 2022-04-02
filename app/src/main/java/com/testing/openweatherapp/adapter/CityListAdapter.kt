package com.testing.openweatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testing.openweatherapp.R
import com.testing.openweatherapp.data.local.database.model.City
import kotlinx.android.synthetic.main.item_city.view.*

interface OnItemClickCallback {
    fun onItemClick(name: String)
}

class CityListAdapter(private val onItemClickCallback: OnItemClickCallback) : RecyclerView.Adapter<CityListAdapter.CityListViewHolder>(){
    private val cityList = mutableListOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityListViewHolder {
        return CityListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityListViewHolder, position: Int) {
        holder.bind(cityList[position], onItemClickCallback)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    fun updateList(list: List<City>) {
        this.cityList.clear()
        this.cityList.addAll(list)
        notifyDataSetChanged()
    }

    class CityListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: City, onItemClickCallback: OnItemClickCallback) {

            itemView.cityNameTextView.text = model.name

            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(model.name)
            }
        }
    }
}