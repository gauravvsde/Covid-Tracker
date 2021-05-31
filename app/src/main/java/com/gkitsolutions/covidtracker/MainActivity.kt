package com.gkitsolutions.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    lateinit var countryAdapter: CountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCountryData()
    }

    private fun getCountryData(){
        val countriesList = CountryData.countryInstance.getData();
        countriesList.enqueue(object : retrofit2.Callback<List<CountryModel>> {
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                Log.d("Error", "data not fetched correctly", t)
            }

            override fun onResponse(call: Call<List<CountryModel>>, response: Response<List<CountryModel>>) {
                val country = response.body()!!
                countryAdapter = CountryAdapter(this@MainActivity, country)
                CountryList.adapter = countryAdapter
                CountryList.layoutManager = LinearLayoutManager(this@MainActivity)
            }

        })
    }


}