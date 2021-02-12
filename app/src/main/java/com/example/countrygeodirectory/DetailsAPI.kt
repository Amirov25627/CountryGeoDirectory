package com.example.countrygeodirectory

import android.provider.ContactsContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DetailsService{
    @GET("rest/v2/name/{countryName}?fullText=true")
    suspend fun detailsList(): DetailsData
}

val detailsRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.eu/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

object DetailsAPI{
    val service:DetailsService by lazy{
        detailsRetrofit.create(DetailsService::class.java)
    }
}