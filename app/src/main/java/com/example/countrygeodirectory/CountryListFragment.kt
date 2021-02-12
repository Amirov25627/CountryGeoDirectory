package com.example.countrygeodirectory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.countrygeodirectory.databinding.ActivityMainBinding
import com.example.countrygeodirectory.databinding.CountryListFragmentBinding

class CountryListFragment:Fragment() {
    private lateinit var binding: CountryListFragmentBinding
    private val sharedViewModel by activityViewModels<SharedViewModel>()
    lateinit var adapter: CountryItemAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CountryListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CountryItemAdapter(sharedViewModel)
        binding.listRV.adapter = adapter
        sharedViewModel.getCountryList()

        sharedViewModel.countryList.observe(viewLifecycleOwner, Observer {
            if(it==null) return@Observer
            showList(it)
        })

         sharedViewModel.goToDetailsLV.observe(viewLifecycleOwner, Observer {
             if(it) {
                 sharedViewModel.setBackable()
                 findNavController().navigate(R.id.action_countryListFragment_to_countryDetails)}
         })

    }

    private fun showList(list: List<CountryData>) {
          adapter.setList(list)
    }
}