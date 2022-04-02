package com.testing.openweatherapp.ui.cityList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.openweatherapp.data.local.database.model.City
import com.testing.openweatherapp.data.local.repository.cityList.CityListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(private val repository: CityListRepository): ViewModel() {

    val cityListData = repository.cities

    fun addCityList() {
        viewModelScope.launch(Dispatchers.IO) {
            val list: List<City> = listOf(
                City("Moscow"),
                City("Saint Petersburg"),
                City("Ryazan"),
                City("Saratov"),
                City("Novosibirsk"),
                City("Tyumen"),
                City("Volgograd"),
                City("Ufa"),
                City("Sochi"),
                City("Grozny"),
                City("Vladimir"),
                City("Pavlovo"),
                City("Vladivostok"),
                City("Omsk"),
                City("Suzdal")
            )
            repository.insertCityList(list)
        }
    }
}