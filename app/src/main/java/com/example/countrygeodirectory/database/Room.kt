package com.example.countrygeodirectory.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountryDao{

    @Query("select * from databasecountry")
    fun getCountries(): LiveData<List<DatabaseCountry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( countries: List<DatabaseCountry>)

}

@Database(entities = [DatabaseCountry::class], version = 1)
abstract class CountriesDatabase: RoomDatabase(){
    abstract val countryDao: CountryDao
}



private lateinit var INSTANCE: CountriesDatabase

fun getDatabase(context: Context):CountriesDatabase{
    synchronized(CountriesDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(context.applicationContext, CountriesDatabase::class.java, "countries").build()
        }
    }
    return INSTANCE
}
