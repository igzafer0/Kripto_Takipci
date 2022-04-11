package com.igzafer.kriptotakipci.service

import com.igzafer.kriptotakipci.model.CryptoModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApi {

    @GET("currencies/ticker")
    fun getPrices(@Query("key") token:String):Single<List<CryptoModel>>
}