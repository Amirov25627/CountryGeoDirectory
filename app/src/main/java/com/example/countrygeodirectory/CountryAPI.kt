package com.example.countrygeodirectory

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountryService{
    @GET("rest/v2/all?fields=name;capital;flag;alpha3Code;subregion;demonym;population;")
    suspend fun countryList():List<CountryData>
    
    
    @GET("rest/v2/name/{countryName}?fullText=true")
    suspend fun detailsList(@Path("countryName") name: String): DetailsData
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
