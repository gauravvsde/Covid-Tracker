package com.gkitsolutions.covidtracker

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://corona.lmao.ninja/"
interface CountryInterface {

    @GET("v2/countries")
    fun getData(): Call<List<CountryModel>>

    @GET("v2/countries/india")
    fun getIndiaData(): Call<CountryModel>

}

object CountryData{
    val countryInstance: CountryInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        countryInstance = retrofit.create(CountryInterface:: class.java)
    }
}