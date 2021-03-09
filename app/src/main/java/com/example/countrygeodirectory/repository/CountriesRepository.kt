package com.example.countrygeodirectory.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.countrygeodirectory.network.CountryAPI
import com.example.countrygeodirectory.database.CountryData
import com.example.countrygeodirectory.database.asDatabaseModel
import com.example.countrygeodirectory.database.CountriesDatabase
import com.example.countrygeodirectory.database.asDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesRepository (private val database: CountriesDatabase) {
    suspend fun refreshCountries() {
        withContext(Dispatchers.IO) {
            val countryList = CountryAPI.service.countryList()
            database.countryDao.insertAll(countryList.asDatabaseModel())
        }
    }

    val countries: LiveData<List<CountryData>> = Transformations.map(database.countryDao.getCountries()){it.asDomainModel()}
}