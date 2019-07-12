package com.ariorick.revoluttestapp.fragment

import android.util.Log
import com.ariorick.revoluttestapp.mvp.BasePresenter
import com.ariorick.revoluttestapp.mvp.MvpView
import com.ariorick.revoluttestapp.net.CurrencySource
import javax.inject.Inject

class CurrencyFragmentPresenter @Inject constructor(
    val currencySource: CurrencySource
) : BasePresenter<View>(), Presenter {


    override fun attachView(mvpView: View) {
        super.attachView(mvpView)
        addDisposable(
            currencySource.getCurrency("RUB")
                .subscribe(
                    { Log.d("lol", it.toString()) },
                    Throwable::printStackTrace
                )
        )
    }


}

interface View : MvpView
