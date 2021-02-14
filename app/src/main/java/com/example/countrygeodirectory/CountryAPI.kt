package com.example.countrygeodirectory

import android.util.Log
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService{

    @GET("rest/v2/all?fields=name;capital;flag")
    suspend fun countryList():List<CountryData>

    @GET("rest/v2/name/{countryName}?fullText=true")
    suspend fun detailsList(@Path("countryName") name: String): List<DetailsData>


}

val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.eu/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

object CountryAPI{
    val service:CountryService by lazy{
        retrofit.create(CountryService::class.java)

    }
}
