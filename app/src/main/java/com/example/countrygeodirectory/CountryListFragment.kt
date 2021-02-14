package com.example.countrygeodirectory

import android.os.Bundle
import android.view.*
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
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

//        binding.filEditText.doOnTextChanged { text,_, _, _ ->
//            val enteredText = text.toString()
//            sharedViewModel.filterByText(enteredText)
//        }

        adapter = CountryItemAdapter(sharedViewModel)
        binding.listRV.adapter = adapter
        sharedViewModel.getCountryList()

        sharedViewModel.status.observe(viewLifecycleOwner, Observer {
            updateStatus(it)
        })

        sharedViewModel.countryList.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            showList(it)
        })

        sharedViewModel.showTextWin.observe(viewLifecycleOwner, Observer {
            if (it == SharedViewModel.WINDOW.SHOW) {showWindow()}
            if (it == SharedViewModel.WINDOW.HIDE) {hideWindow()}
        })

        sharedViewModel.goToDetailsLV.observe(viewLifecycleOwner, Observer {
            if (it) {
                sharedViewModel.setBackable()

                findNavController().navigate(R.id.action_countryListFragment_to_countryDetails)
            }
        })

    }


    private fun showList(list: List<CountryData>) {
        adapter.setList(list)
    }

    private fun updateStatus(status: SharedViewModel.STATUS) {
        when (status) {
            SharedViewModel.STATUS.LOADING -> {
                binding.listRV.visibility = View.GONE
                binding.statusIV.visibility = View.VISIBLE
                binding.statusIV.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.loading_animation))
            }
            SharedViewModel.STATUS.SUCCESS -> {
                binding.listRV.visibility = View.VISIBLE
                binding.statusIV.visibility = View.GONE
            }
            SharedViewModel.STATUS.ERROR ->{
                binding.listRV.visibility = View.GONE
                binding.statusIV.visibility = View.VISIBLE
                binding.statusIV.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_connection_error))
            }
        }
    }

    private fun showWindow(){
        binding.filBack.visibility = View.VISIBLE
        binding.filEditText.visibility = View.VISIBLE
    }

    private fun hideWindow(){
        binding.filBack.visibility = View.GONE
        binding.filEditText.visibility = View.GONE
    }
    





}


