package com.ariorick.revoluttestapp.view

import com.ariorick.revoluttestapp.dagger.CurrencyFragmentScope
import com.ariorick.revoluttestapp.model.Currency
import com.ariorick.revoluttestapp.mvp.BasePresenter
import com.ariorick.revoluttestapp.mvp.MvpView
import com.ariorick.revoluttestapp.net.CurrencySource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@CurrencyFragmentScope
class CurrencyFragmentPresenter @Inject constructor(
    private val currencySource: CurrencySource,
    private val currencyAdapter: CurrencyAdapter
) : BasePresenter<View>(), Presenter {

    private val defaultBase = "EUR"
    private val defaultValue = 1f
    private var selectedBase = defaultBase

    private lateinit var userEventSubject: ReplaySubject<Pair<String, Float>>


    override fun attachView(mvpView: View) {
        super.attachView(mvpView)

        userEventSubject = ReplaySubject.create()
        val userEventFlowable = userEventSubject.toFlowable(BackpressureStrategy.LATEST)


        addDisposable(createCalculatedCurrencyObservable(
            createLoadCurrencyConnectableFlowable(),
            userEventFlowable
        ).subscribe(
            {
                currencyAdapter.setData(it)
                //if (baseChanged) view?.scrollUp()
            },
            {
                it.printStackTrace()
                view?.showConnectionError()
            }
        ))

        userEventSubject.onNext(Pair(defaultBase, defaultValue))
        currencyAdapter.setBase = { base, value ->
            selectedBase = base
            userEventSubject.onNext(Pair(base, value))
        }
    }

    private fun createLoadCurrencyConnectableFlowable(): Flowable<List<Currency>> {
        return Flowable.interval(1, TimeUnit.SECONDS)
            .flatMapSingle { currencySource.getCurrency(selectedBase) }
            .retry { t: Throwable ->
                view?.showConnectionError()
                true
            }
            .doOnNext { view?.hideConnectionError() }
    }

    private fun createCalculatedCurrencyObservable(
        currencyFlowable: Flowable<List<Currency>>,
        userEventFlowable: Flowable<Pair<String, Float>>
    ): Flowable<List<Currency>> {
        return Flowable.combineLatest<List<Currency>, Pair<String, Float>, Single<List<Currency>>>(
            currencyFlowable,
            userEventFlowable,
            BiFunction { list, baseAndValuePair ->
                if (list[0].base == baseAndValuePair.first) {
                    Flowable.fromIterable(list)
                        .map {
                            Currency(
                                it.name,
                                baseAndValuePair.first,
                                it.value * baseAndValuePair.second
                            )
                        }.toList()
                } else Single.just(ArrayList())
            }
        ).flatMapSingle { it }
            .filter { it.isNotEmpty() }
            .map { it.sorted() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}

interface View : MvpView {

    fun scrollUp()

    fun showConnectionError()

    fun hideConnectionError()

}
