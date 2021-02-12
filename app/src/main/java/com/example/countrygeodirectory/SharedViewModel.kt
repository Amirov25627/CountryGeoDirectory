package com.example.countrygeodirectory

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel() {
val countryList = MutableLiveData<List<CountryData>>()
val detailsList = MutableLiveData<DetailsData>()
val goToDetailsLV = MutableLiveData<Boolean>()
val currentData = MutableLiveData<CountryData>()



    fun getCountryList(){
        viewModelScope.launch{

            try{
                val list = CountryAPI.service.countryList()
                countryList.value = list
            } catch (e:Exception){
                countryList.value = null
            }

        }

    }

    fun getDetailsList(){
        Log.d("CN ", countryName )
        viewModelScope.launch{

            try{
                val data = CountryAPI.service.detailsList(countryName)
                detailsList.value = data
            } catch (e:Exception){
                Log.e("SRV, $countryName", e.toString())
                detailsList.value = null
            }
        }
    }


fun goToDetails(data: CountryData){
    goToDetailsLV.value = true
    currentData.value = data

}

    fun setBackable(){
        goToDetailsLV.value = false
    }
}



