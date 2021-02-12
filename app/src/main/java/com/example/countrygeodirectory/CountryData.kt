package com.example.countrygeodirectory

import com.google.gson.annotations.SerializedName

data class CountryData (
@SerializedName("name") val name: String,
@SerializedName("capital") val capital: String,
@SerializedName("alpha3Code") val alpha3Code: String,
@SerializedName("subregion") val subregion: String,
@SerializedName("population") val population: Int,
@SerializedName("demonym") val demonym: String
)