package com.ariorick.revoluttestapp

import android.os.Bundle
import com.ariorick.revoluttestapp.view.CurrencyFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CurrencyFragment())
            .commit()

    }
}
