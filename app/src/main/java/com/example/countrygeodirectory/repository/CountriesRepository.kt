package com.example.countrygeodirectory.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.countrygeodirectory.CountryAPI
import com.example.countrygeodirectory.CountryData
import com.example.countrygeodirectory.CountryService
import com.example.countrygeodirectory.asDatabaseModel
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