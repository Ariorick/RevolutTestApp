package com.ariorick.revoluttestapp.dagger

import android.content.Context
import com.ariorick.revoluttestapp.App
import com.ariorick.revoluttestapp.CURRENCY_SERVER_ADDRESS
import com.ariorick.revoluttestapp.model.Currency
import com.ariorick.revoluttestapp.net.CurrencyTypeAdapter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun bindContext(app: App): Context = app


    @Provides
    @Singleton
    internal fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CURRENCY_SERVER_ADDRESS)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeHierarchyAdapter(List::class.java as Class<List<Currency>>, CurrencyTypeAdapter())
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


}