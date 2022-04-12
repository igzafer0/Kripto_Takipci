package com.igzafer.kriptotakipci.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igzafer.kriptotakipci.model.CryptoModel
import com.igzafer.kriptotakipci.service.ApiService
import com.igzafer.kriptotakipci.util.ApiStatus
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel(){
    private val apiService = ApiService()
    val disposable = CompositeDisposable()
    val price_data = MutableLiveData<List<CryptoModel>>()
    val price_status = MutableLiveData<ApiStatus>()

    fun refreshData() {
        getDataFromApi()
    }

    private fun getDataFromApi() {
        price_status.value = ApiStatus.LOADING
        disposable.add(
            apiService.getPrices().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith  (object : DisposableSingleObserver<List<CryptoModel>>() {
                    override fun onSuccess(t: List<CryptoModel>) {
                        price_status.value = ApiStatus.SUCCESS
                        price_data.value=t
                    }

                    override fun onError(e: Throwable) {
                        price_status.value = ApiStatus.FAILED
                        Log.d("winter",e.message.toString())
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}