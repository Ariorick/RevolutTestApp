package com.ariorick.revoluttestapp.view

import android.util.Log
import com.ariorick.revoluttestapp.dagger.CurrencyFragmentScope
import com.ariorick.revoluttestapp.mvp.BasePresenter
import com.ariorick.revoluttestapp.mvp.MvpView
import com.ariorick.revoluttestapp.net.CurrencySource
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@CurrencyFragmentScope
class CurrencyFragmentPresenter @Inject constructor(
    private val currencySource: CurrencySource
) : BasePresenter<View>(), Presenter {

    private val defaultBase = "RUB"

    private var selectedBase = defaultBase


    override fun attachView(mvpView: View) {
        super.attachView(mvpView)
    }

    fun loadCurrency(base: String) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .flatMapSingle { currencySource.getCurrency(selectedBase) }
            .subscribeOn(Schedulers.io())


            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.d("lol", it.toString()) },
                Throwable::printStackTrace
            )
    }

    fun setBase(base: String) {
        selectedBase = base
        loadCurrency(base)
    }

}

interface View : MvpView
