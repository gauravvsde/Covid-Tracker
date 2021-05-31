package com.gkitsolutions.covidtracker

data class CountryModel(
    val country: String,
    val countryInfo: CountryInfo,
    val cases: String,
    val todayCases: String,
    val deaths:String,
    val todayDeaths: String,
    val recovered: String,
    val active: String,
    val updated: String
)
