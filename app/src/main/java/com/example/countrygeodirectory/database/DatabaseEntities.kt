package com.example.countrygeodirectory.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.countrygeodirectory.CountryData

@Entity
data class DatabaseCountry constructor(
    @PrimaryKey
    val name: String,
    val capital: String,
    val flag: String
)


fun List<DatabaseCountry>.asDomainModel(): List<CountryData> {
    return map {
        CountryData(
            name = it.name,
            capital = it.capital,
            flag = it.flag)
    }
}

