package com.example.countrygeodirectory.database

import com.google.gson.annotations.SerializedName

data class CountryData (
@SerializedName("name") val name: String,
@SerializedName("capital") val capital: String,
@SerializedName("flag") val flag: String
)

fun List<CountryData>.asDatabaseModel(): List<DatabaseCountry> {
    return map {
        DatabaseCountry(
            name = it.name,
            capital = it.capital,
            flag = it.flag)
    }
}