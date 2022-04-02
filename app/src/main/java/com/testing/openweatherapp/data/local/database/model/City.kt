package com.testing.openweatherapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_list")
data class City (
        @PrimaryKey val name: String)