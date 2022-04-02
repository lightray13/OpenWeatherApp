package com.testing.openweatherapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testing.openweatherapp.data.local.database.model.Converter
import com.testing.openweatherapp.data.local.database.dao.CityDao
import com.testing.openweatherapp.data.local.database.dao.WeatherDao
import com.testing.openweatherapp.data.local.database.model.City
import com.testing.openweatherapp.data.local.database.model.WeatherEntity

@Database(entities = [WeatherEntity::class, City::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converter::class])
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao

    companion object{
        fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(context, WeatherDatabase::class.java, "Weather")
                .build()
        }
    }
}