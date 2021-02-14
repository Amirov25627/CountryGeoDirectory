package com.example.countrygeodirectory

import com.google.gson.annotations.SerializedName

data class CountryData (
@SerializedName("name") val name: String,
@SerializedName("capital") val capital: String,
@SerializedName("flag") val flag: String
)