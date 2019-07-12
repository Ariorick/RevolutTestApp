package com.ariorick.revoluttestapp.net

import com.ariorick.revoluttestapp.model.Currency
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest")
    fun getCurrency(@Query("base") base: String): Single<List<Currency>>

}
