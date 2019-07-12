package com.ariorick.revoluttestapp.dagger

import com.ariorick.revoluttestapp.MainActivity
import com.ariorick.revoluttestapp.view.CurrencyFragment
import com.ariorick.revoluttestapp.view.CurrencyFragmentPresenter
import com.ariorick.revoluttestapp.view.Presenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Module
interface ActivityProvider {

    @ContributesAndroidInjector(modules = [FragmentProvider::class])
    fun provideMainActivity(): MainActivity

}

@Module
interface FragmentProvider {

    @CurrencyFragmentScope
    @ContributesAndroidInjector
    fun provideCurrencyFragment(): CurrencyFragment

    @Binds
    fun bindPresenter(currencyFragmentPresenter: CurrencyFragmentPresenter): Presenter

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class CurrencyFragmentScope



