package com.example.kotlincountries.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.kotlincountries.model.Country
import com.example.kotlincountries.service.CountryAPIService
import com.example.kotlincountries.service.CountryDatabase
import com.example.kotlincountries.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application): BaseViewModel(application) {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()
    private var customPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10*60*1000*1000*1000L //10 dk yi nanasaniyeye çevirdik

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val updateTime = customPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){ // 10 dk geçmediyse SQLite al
            getDataFromSQLite()
        }else{ // 10 dk olduysa İnternetten çek
            getDataFromAPI()
        }
    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite(){
        countryLoading.value=true
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private fun getDataFromAPI(){
        countryLoading.value = true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread()) //"Schedulers.newThread()" yeni thrette
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        countryError.value=true
                        countryLoading.value=false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun showCountries(countryList : List<Country>){
        countries.value=countryList
        countryError.value=false
        countryLoading.value=false
    }

    private fun storeInSQLite(list : List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            //veriyi kaydediyoruz sonra idler geliyor
            val listlong =dao.insertAll(*list.toTypedArray()) //"toTypedArray()" listeyi tekil elemana indirir tek tek ->individual

            var i = 0
            while (i<list.size){
                list[i].uuid = listlong[i].toInt()
                i++
            }

            showCountries(list)
        }

        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() { //Fragment kapanınca hafızayı temizler
        super.onCleared()
        disposable.clear()
    }
}