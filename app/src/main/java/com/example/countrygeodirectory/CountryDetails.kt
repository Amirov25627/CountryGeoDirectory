package com.example.countrygeodirectory

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.countrygeodirectory.databinding.CountryDetailsBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

lateinit var countryName : String

class CountryDetails:Fragment() {
    private lateinit var binding: CountryDetailsBinding
    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getDetailsList()
        sharedViewModel.currentData.observe(viewLifecycleOwner, Observer {
            if(it==null) return@Observer
            countryName = it.name
            })

        sharedViewModel.detailsList.observe(viewLifecycleOwner, Observer {
            if (it==null) return@Observer
            showDetails(it)
        })

    }



    private fun  showDetails(data: DetailsData) {
        binding.capital.text = data.capital
        binding.countryNameBig.text = data.name
        binding.subregion.text = data.subregion
        binding.demonim.text = data.demonym
        binding.population.text = data.population.toString()
        binding.language.text = data.languages.toString()
        binding.currency.text = data.currencies.toString()




        val uri = Uri.parse("https://restcountries.eu/data/${data.alpha3Code.toLowerCase()}.svg")
        GlideToVectorYou
               .init()
               .with(binding.root.context)
               //.setPlaceHolder(placeholderLoading, placeholderError)
               .load(uri, binding.flagIconBig)

    }


}



