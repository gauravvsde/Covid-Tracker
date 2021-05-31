package com.gkitsolutions.covidtracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class CountryAdapter(private val context: Context, private val countries: List<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countries[position]
        with(holder){
            totalCases.text = country.cases
            activeCases.text = country.active
            deathCases.text = country.deaths
            recoveredCases.text = country.recovered
            countryName.text = country.country
        }

        Glide.with(context).load(country.countryInfo.flag).into(holder.flagImage)
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view){
        val flagImage = view.findViewById<ImageView>(R.id.flag_image)
        val totalCases = view.findViewById<TextView>(R.id.total_cases)
        val activeCases = view.findViewById<TextView>(R.id.active_cases)
        val deathCases = view.findViewById<TextView>(R.id.death_cases)
        val recoveredCases = view.findViewById<TextView>(R.id.recovered_cases)
        val countryName = view.findViewById<TextView>(R.id.country_name)
    }
}