package com.ariorick.revoluttestapp.net

import com.ariorick.revoluttestapp.model.Currency
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class CurrencySource @Inject constructor(retrofit: Retrofit) {

    private val currencyService: CurrencyService = retrofit.create(CurrencyService::class.java)

    fun getCurrency(base: String): Single<List<Currency>> = currencyService.getCurrency(base)

}