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
val countryList = MutableLiveData<List<CountryData>>()
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
                countryList.value = countriesRepository.countries.value
                status.value = STATUS.SUCCESS
            } catch (e:Exception){
                countryList.value = null
                status.value = STATUS.ERROR
            }

        }

    }

    fun getDetailsList(){

        viewModelScope.launch{
            Log.d("CN ", countryName )
            try{
                val list = CountryAPI.service.detailsList(countryName)
                detailsList.value = list
                Log.d("LIST ", list.toString() )
            } catch (e:Exception){
                detailsList.value = null
                Log.d("ERROR ", e.toString() )
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





