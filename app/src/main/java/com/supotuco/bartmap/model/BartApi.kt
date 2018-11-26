package com.supotuco.bartmap.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object BartREST {
    const val API_KEY = "MW9S-E7SL-26DU-VV8V"
    const val ROOT_URL = "https://api.bart.gov/api/"

    private var _service: BartService? = null

    fun service(): BartService {
        if (_service == null) {
            _service = Retrofit.Builder()
                    .baseUrl(ROOT_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BartService::class.java)
        }

        return _service!!
    }
}

interface BartService {

    @GET("stn.aspx?cmd=stns&json=y")
    fun stations(@Query("key") apiKey: String): Single<ServerBartStationResponse>
}