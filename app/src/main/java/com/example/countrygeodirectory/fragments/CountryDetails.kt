package com.example.countrygeodirectory.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.countrygeodirectory.database.DetailsData
import com.example.countrygeodirectory.R
import com.example.countrygeodirectory.viewmodel.SharedViewModel
import com.example.countrygeodirectory.databinding.CountryDetailsBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import java.text.NumberFormat


class CountryDetails:Fragment() {
    private lateinit var binding: CountryDetailsBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel.getDetailsList()

        Log.d("CREATED", context.toString() )
        sharedViewModel.detailsList.observe(viewLifecycleOwner, Observer {
            if (it==null) return@Observer
            showDetails(it.first())

        })
    }



    private fun  showDetails(data: DetailsData) {
        binding.capital.text = data.capital
        binding.countryNameBig.text = data.name
        binding.subregion.text = data.subregion
        binding.demonim.text = data.demonym
        binding.population.text = NumberFormat.getNumberInstance().format(data.population)
        binding.language.text = data.languages.first().name
        binding.currency.text = data.currencies.first().name
        binding.currencySymbol.text = data.currencies.first().symbol


        val uri = Uri.parse(data.flag)
        GlideToVectorYou
               .init()
               .with(binding.root.context)
               .setPlaceHolder(R.drawable.loading_animation, R.drawable.ic_broken_image)
               .load(uri, binding.flagIconBig)



        binding.capital.setOnClickListener {
            val capitalUri = Uri.parse("https://en.wikipedia.org/wiki/${data.capital}")
            val searchIntent = Intent(Intent.ACTION_VIEW)
            searchIntent.data = capitalUri
            binding.root.context.startActivity(searchIntent)
        }

        binding.countryNameBig.setOnClickListener {
            val capitalUri = Uri.parse("https://en.wikipedia.org/wiki/${data.name}")
            val searchIntent = Intent(Intent.ACTION_VIEW)
            searchIntent.data = capitalUri
            binding.root.context.startActivity(searchIntent)
        }

        binding.subregion.setOnClickListener {
            val capitalUri = Uri.parse("https://en.wikipedia.org/wiki/${data.subregion}")
            val searchIntent = Intent(Intent.ACTION_VIEW)
            searchIntent.data = capitalUri
            binding.root.context.startActivity(searchIntent)
        }

        binding.demonim.setOnClickListener {
            val capitalUri = Uri.parse("https://en.wikipedia.org/wiki/${data.demonym}")
            val searchIntent = Intent(Intent.ACTION_VIEW)
            searchIntent.data = capitalUri
            binding.root.context.startActivity(searchIntent)
        }

        binding.language.setOnClickListener {
            val capitalUri = Uri.parse("https://en.wikipedia.org/wiki/${data.languages.first().name}")
            val searchIntent = Intent(Intent.ACTION_VIEW)
            searchIntent.data = capitalUri
            binding.root.context.startActivity(searchIntent)
        }

        binding.currency.setOnClickListener {
            val capitalUri = Uri.parse("https://en.wikipedia.org/wiki/${data.currencies.first().name}")
            val searchIntent = Intent(Intent.ACTION_VIEW)
            searchIntent.data = capitalUri
            binding.root.context.startActivity(searchIntent)


        }

        binding.currencySymbol.setOnClickListener {
            val capitalUri = Uri.parse("https://en.wikipedia.org/wiki/${data.currencies.first().name}")
            val searchIntent = Intent(Intent.ACTION_VIEW)
            searchIntent.data = capitalUri
            binding.root.context.startActivity(searchIntent)
    }
  }
}



