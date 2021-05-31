package com.gkitsolutions.covidtracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_launching.*
import org.eazegraph.lib.models.PieModel
import retrofit2.Call
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class LaunchingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launching)
        getIndiaCountryData()

        all_country_data.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getIndiaCountryData(){
        loader.start()
        val indiaCountry = CountryData.countryInstance.getIndiaData();
        indiaCountry.enqueue(object : retrofit2.Callback<CountryModel> {
            override fun onFailure(call: Call<CountryModel>, t: Throwable) {
                Log.d("IndiaError", "data not fetched correctly", t)
            }

            override fun onResponse(call: Call<CountryModel>, response: Response<CountryModel>) {
                loader.stop()
                loader.visibility = View.GONE
                val indiaData = response.body()!!
                main_total_cases.text = indiaData.cases
                main_deaths.text = indiaData.deaths
                main_recovered.text = indiaData.recovered
                main_active.text = indiaData.active
                main_today_cases.text = indiaData.todayCases
                Glide.with(india_image).load(indiaData.countryInfo.flag).into(india_image)

                val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")
                val now = System.currentTimeMillis()

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = now

                val newTime = formatter.format(calendar.time)
                time_zone.text = newTime

                pie_chart.addPieSlice(PieModel("Cases", indiaData.cases.toFloat(), Color.parseColor("#ffb259")))
                pie_chart.addPieSlice(PieModel("Deaths", indiaData.deaths.toFloat(), Color.parseColor("#ff5959")))
                pie_chart.addPieSlice(PieModel("Recovered", indiaData.recovered.toFloat(), Color.parseColor("#4cd97b")))
                pie_chart.addPieSlice(PieModel("Active Cases", indiaData.active.toFloat(), Color.parseColor("#4cb5ff")))
                pie_chart.addPieSlice(PieModel("Today Cases", indiaData.todayCases.toFloat(), Color.parseColor("#9059ff")))

                pie_chart.startAnimation()
            }
        })
    }
}