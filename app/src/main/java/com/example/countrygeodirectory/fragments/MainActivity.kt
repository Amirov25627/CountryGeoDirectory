package com.example.countrygeodirectory.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.countrygeodirectory.viewmodel.Factory
import com.example.countrygeodirectory.R
import com.example.countrygeodirectory.viewmodel.SharedViewModel

lateinit var viewModel: SharedViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        val factory = Factory(application)
        viewModel = ViewModelProvider(this, factory).get(SharedViewModel::class.java)
    }

    var showed = false

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if (item.itemId == R.id.search_layout){
            if (!showed){
                showed = true
                viewModel.showEditTextWindow()

            } else {
                showed = false
                viewModel.hideEditTextWindow()
                viewModel.getCountryList()




            }
        }
        return true
    }


}