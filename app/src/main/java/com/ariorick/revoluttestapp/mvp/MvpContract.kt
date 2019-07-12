package com.ariorick.revoluttestapp.mvp

interface MvpPresenter<V : MvpView> {

    /**
     * Method to be called for the presenter internal initialization
     * such as db or internet request and etc.
     * It should be called in onCreate() callback
     */
    fun prepare()

    /**
     * Method to be called before android component will be destroyed in most cases
     */
    fun destroy()

    /**
     * Called when the view has already created
     *
     * @param mvpView reference to view component
     */
    fun attachView(mvpView: V)

    /**
     * Called when the view is going to be destroyed
     */
    fun detachView()
}

interface MvpView
