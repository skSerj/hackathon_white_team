package com.sourceit.whiteteam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sourceit.whiteteam.R

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    private val weatherList = ArrayList<>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout., parent, false)
        return WeatherHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather:) {

        }
    }
}