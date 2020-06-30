package com.sourceit.whiteteam.network

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiService {
    private const val END_POINT: String = "http://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "fed403fe4bf340fa270d9a48cfa0b2cf"
    private const val CITY = "Kharkiv"
    private const val LANGUAGE = "ru"
    private const val UNITS = "metric"
    private var weatherApi: WeatherApi

    fun getFiveDayWeather() = weatherApi.fiveDays(CITY, LANGUAGE, UNITS, API_KEY)

    interface WeatherApi {
        @GET("forecast")
        fun fiveDays(@Query("q") city: String,
        @Query("lang") lang: String,
        @Query("units") units: String,
        @Query("appid") appid: String): Single<RequestModel>
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(END_POINT)
            .client(client)
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }
}