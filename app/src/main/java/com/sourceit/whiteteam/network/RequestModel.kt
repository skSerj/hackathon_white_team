package com.sourceit.whiteteam.network

import com.google.gson.annotations.SerializedName

data class RequestModel(
    @SerializedName("list")
    var list: List<WeatherInfo>
)

data class WeatherInfo(
    var main: Main,
    var weather: List<Weather>,
    var clouds: Clouds,
    var wind: Wind,
    @SerializedName("dt_txt")
    var dtTxt: String
)

data class Main(
    var temp: Float,
    @SerializedName("feels_like")
    var feelsLike: Float,
    var tempMin: Float,
    var tempMax: Float,
    var pressure: Int,
    var humidity: Int
)

data class Wind(
    var speed: Float,
    var deg: Int
)

data class Clouds(var all: Int)

data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)



