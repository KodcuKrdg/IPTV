package com.example.kotlincountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlincountries.model.Country

@Database(entities = arrayOf(Country::class),version = 1)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao() : CountryDao

    companion object{
        @Volatile private var instance : CountryDatabase? = null //@Volatile -> tüm thredlere görnür hale gelir iki farklı thread kulandığımız için
        private val lock = Any()
        //üsteki "intance" olup olmadığını kontrol eder ve varsa onu yoksa yenisini yapar
        operator fun invoke(context: Context) = instance?: synchronized(lock) { //"synchronized" farklı treadler aynı anda gelirse önce birine sonra birine
            instance?: makeDatabase(context).also { //"also" -> bunu yap sonra birde şunu yap demek
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,CountryDatabase::class.java,"countrydatabase").build()
    }
}