package com.example.countrygeodirectory

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.countrygeodirectory.database.getDatabase
import com.example.countrygeodirectory.repository.CountriesRepository
import kotlinx.coroutines.launch

class SharedViewModel(app: Application) :ViewModel() {
private var countriesRepository = CountriesRepository(getDatabase(app))
val countryList = countriesRepository.countries
val detailsList = MutableLiveData<List<DetailsData>>()
val goToDetailsLV = MutableLiveData<Boolean>()
private val currentData = MutableLiveData<CountryData>()
private var countryName = ""
val status = MutableLiveData<STATUS>()
var showTextWin = MutableLiveData<WINDOW>()
var filteredCountryList = MutableLiveData<List<CountryData>>()


    fun getCountryList(){
        viewModelScope.launch{
        status.value = STATUS.LOADING
        showTextWin.value = WINDOW.HIDE
            try{
                //val list = CountryAPI.service.countryList()
                //countryList.value = list
                countriesRepository.refreshCountries()
                status.value = STATUS.SUCCESS
            } catch (e:Exception){
                if(countryList.value.isNullOrEmpty())
                status.value = STATUS.ERROR
                else
                    status.value = STATUS.SUCCESS
            }

        }

    }

    fun getDetailsList(){

        Log.d("GD", countryName )

        viewModelScope.launch{
            try{
                val list = CountryAPI.service.detailsList(countryName)
                detailsList.value = list
                Log.d("LIST ", list.toString() )
            } catch (e:Exception){
                detailsList.value = null
                Log.d("ER ", countryName )
            }
        }
    }


fun goToDetails(data: CountryData){
    goToDetailsLV.value = true
    currentData.value = data
    countryName = currentData.value?.name.toString()


    


}

    fun setBackable(){

        goToDetailsLV.value = false

    }


    enum class STATUS{
        LOADING,
        ERROR,
        SUCCESS
    }


    enum class WINDOW{
        SHOW,
        HIDE
    }

    fun showEditTextWindow(){
        showTextWin.value = WINDOW.SHOW
     }

    fun hideEditTextWindow(){
        showTextWin.value = WINDOW.HIDE
    }

fun filterByText(text: String){
        filteredCountryList.value = countryList.value?.filter { it.name.startsWith(text, true) }
        }



}

class Factory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SharedViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}





