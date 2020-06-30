package com.sourceit.whiteteam.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sourceit.whiteteam.R
import com.sourceit.whiteteam.network.RequestModel
import com.sourceit.whiteteam.network.WeatherInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_allweather.view.*

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    private val weatherList = ArrayList<WeatherInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_allweather, parent, false)
        return WeatherHolder(itemView)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    fun update(weather: List<WeatherInfo>) {
        weatherList.clear()
        weatherList.addAll(weather)
        notifyDataSetChanged()
    }

    class WeatherHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: WeatherInfo) {
            itemView.day_info.text = weather.dtTxt
            itemView.weather_temp.text = weather.main.temp.toString()
            itemView.weather_info.text = weather.weather[0].description
            itemView.weather_temp_feel.text = weather.main.feelsLike.toString()
            Picasso.with(itemView.context)
                .load("http://openweathermap.org/img/wn/${weather.weather[0].icon}.png")
                .into(itemView.weather_image)
        }
    }
}