package com.testing.openweatherapp.data.local.database.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.testing.openweatherapp.api.model.Weather
import java.lang.reflect.Type

class Converter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun listToString(list: List<Weather>): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

        @TypeConverter
        @JvmStatic
        fun stringToList(data: String): List<Weather> {
            val listType: Type = object: TypeToken<List<Weather?>?>(){}.type
            return Gson().fromJson(data, listType)
        }
    }
}