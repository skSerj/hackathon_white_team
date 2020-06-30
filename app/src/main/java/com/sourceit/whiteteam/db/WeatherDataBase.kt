//package com.sourceit.whiteteam.db
//
//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//
//
//@Database(entities =, version = 1, exportSchema = false)
//abstract class WeatherDataBase : RoomDatabase() {
//
//
//    abstract fun getWeatherDao(): WeatherDao
//
//    companion object {
//        fun getInstance(context: Context) = Room.databaseBuilder(
//            context.applicationContext,
//            WeatherDataBase::class.java,
//            "weather"
//        ).fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
//            .build()
//    }
//}