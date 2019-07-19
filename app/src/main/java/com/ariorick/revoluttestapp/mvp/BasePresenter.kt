package com.ariorick.revoluttestapp.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : MvpView> : MvpPresenter<T> {

    protected var view: T? = null

    private val viewDependingCompositeDisposable = CompositeDisposable()
    private val wholeComponentLifecycleCompositeDisposable = CompositeDisposable()

    val isViewAttached: Boolean
        get() = view != null

    override fun prepare() {

    }

    override fun destroy() {
        wholeComponentLifecycleCompositeDisposable.clear()
    }

    override fun attachView(mvpView: T) {
        view = mvpView
    }

    override fun detachView() {
        viewDependingCompositeDisposable.clear()
        view = null
    }

    protected fun addDisposable(disposable: Disposable) {
        viewDependingCompositeDisposable.add(disposable)
    }

    protected fun addViewIndependentDisposable(disposable: Disposable) {
        wholeComponentLifecycleCompositeDisposable.add(disposable)
    }

    class MvpViewNotAttachedException :
        RuntimeException("Please call Presenter.attachView(MvpView) before requesting data to the Presenter")
}
