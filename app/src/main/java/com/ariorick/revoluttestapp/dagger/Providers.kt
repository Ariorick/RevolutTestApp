package com.ariorick.revoluttestapp.dagger

import com.ariorick.revoluttestapp.MainActivity
import com.ariorick.revoluttestapp.fragment.CurrencyFragment
import com.ariorick.revoluttestapp.fragment.CurrencyFragmentPresenter
import com.ariorick.revoluttestapp.fragment.Presenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityProvider {

    @ContributesAndroidInjector(modules = [FragmentProvider::class])
    fun provideMainActivity(): MainActivity

}

@Module
interface FragmentProvider {

    @ContributesAndroidInjector
    fun provideCurrencyFragment(): CurrencyFragment

    @Binds
    fun bindPresenter(currencyFragmentPresenter: CurrencyFragmentPresenter): Presenter

}



